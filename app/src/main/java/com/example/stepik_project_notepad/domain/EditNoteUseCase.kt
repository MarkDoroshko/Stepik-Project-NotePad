package com.example.stepik_project_notepad.domain

class EditNoteUseCase(
    private val repository: NotesRepository
) {
    operator fun invoke(note: Note) {
        repository.editNote(note)
    }
}