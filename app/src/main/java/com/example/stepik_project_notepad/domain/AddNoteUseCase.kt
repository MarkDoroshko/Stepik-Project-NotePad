package com.example.stepik_project_notepad.domain

class AddNoteUseCase(
    private val repository: NotesRepository
) {
    operator fun invoke(note: Note) {
        repository.addNote(note)
    }
}