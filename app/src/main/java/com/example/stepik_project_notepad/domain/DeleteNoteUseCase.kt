package com.example.stepik_project_notepad.domain

class DeleteNoteUseCase(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int) {
        repository.deleteNote(noteId)
    }
}