package com.example.stepik_project_notepad.domain

class SwitchPinnedStatusUseCase(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int) {
        repository.switchPinnedStatus(noteId)
    }
}