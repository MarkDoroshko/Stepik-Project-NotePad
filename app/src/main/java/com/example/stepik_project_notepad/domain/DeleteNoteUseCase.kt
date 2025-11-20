package com.example.stepik_project_notepad.domain

class DeleteNoteUseCase(
    private val repository: NotesRepository
) {
    operator fun invoke(noteId: Int) {
        repository.deleteNote(noteId)
    }
}