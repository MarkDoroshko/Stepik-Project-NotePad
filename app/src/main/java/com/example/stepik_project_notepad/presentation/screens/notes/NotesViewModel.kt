@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.stepik_project_notepad.presentation.screens.notes

import androidx.lifecycle.ViewModel
import com.example.stepik_project_notepad.data.TestNotesRepositoryImpl
import com.example.stepik_project_notepad.domain.AddNoteUseCase
import com.example.stepik_project_notepad.domain.DeleteNoteUseCase
import com.example.stepik_project_notepad.domain.EditNoteUseCase
import com.example.stepik_project_notepad.domain.GetAllNotesUseCase
import com.example.stepik_project_notepad.domain.GetNoteUseCase
import com.example.stepik_project_notepad.domain.Note
import com.example.stepik_project_notepad.domain.SearchNotesUseCase
import com.example.stepik_project_notepad.domain.SwitchPinnedStatusUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class NotesViewModel : ViewModel() {
    private val repository = TestNotesRepositoryImpl  // НЕЛЬЗЯ!!!

    private val addNoteUseCase = AddNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val getAllNotesUseCase = GetAllNotesUseCase(repository)
    private val getNoteUseCase = GetNoteUseCase(repository)
    private val searchNotesUseCase = SearchNotesUseCase(repository)
    private val switchPinnedStatusUseCase = SwitchPinnedStatusUseCase(repository)

    private val query = MutableStateFlow("")

    private val _state = MutableStateFlow(NotesScreenState())
    val state = _state.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        addSomeNotes()

        query
            .onEach { input ->
                _state.update { it.copy(query = input) }
            }
            .flatMapLatest { input ->
                if (input.isBlank()) getAllNotesUseCase()
                else searchNotesUseCase(input)
            }
            .onEach { notes ->
                val pinnedNotes = notes.filter { it.isPinned }
                val otherNotes = notes.filter { !it.isPinned }
                _state.update { it.copy(pinnedNotes = pinnedNotes, otherNotes = otherNotes) }
            }
            .launchIn(scope)
    }

    // TODO: don't forget to remove it
    private fun addSomeNotes() {
        repeat(50_000) {
            addNoteUseCase(title = "Title №$it", content = "Content №$it")
        }
    }

    fun processCommand(command: NotesCommand) {
        when (command) {
            is NotesCommand.DeleteNote -> {
                deleteNoteUseCase(command.noteId)
            }

            is NotesCommand.EditNote -> {
                val note = getNoteUseCase(command.note.id)
                val title = note.title
                editNoteUseCase(note.copy(title = title + " edited"))
            }

            is NotesCommand.InputSearchQuery -> {
                query.update { command.query.trim() }
            }

            is NotesCommand.SwitchPinnedStatus -> {
                switchPinnedStatusUseCase(command.noteId)
            }

        }
    }
}

sealed interface NotesCommand {
    data class InputSearchQuery(val query: String) : NotesCommand

    data class SwitchPinnedStatus(val noteId: Int) : NotesCommand

    // Temp

    data class DeleteNote(val noteId: Int) : NotesCommand

    data class EditNote(val note: Note) : NotesCommand
}

data class NotesScreenState(
    val query: String = "",
    val pinnedNotes: List<Note> = listOf(),
    val otherNotes: List<Note> = listOf()
)