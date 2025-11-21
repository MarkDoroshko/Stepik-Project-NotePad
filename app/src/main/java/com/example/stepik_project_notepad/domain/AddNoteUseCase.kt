package com.example.stepik_project_notepad.domain

class AddNoteUseCase(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String
    ) {
        repository.addNote(
            title = title,
            content = content,
            isPinned = false,
            updatedAt = System.currentTimeMillis()
        )
    }
}