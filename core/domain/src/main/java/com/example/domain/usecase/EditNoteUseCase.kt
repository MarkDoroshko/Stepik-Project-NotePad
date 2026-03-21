package com.example.domain.usecase

import com.example.domain.entity.Note
import com.example.domain.repository.NotesRepository
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.editNote(
            note.copy(
                updatedAt = System.currentTimeMillis()
            )
        )
    }
}