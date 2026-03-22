package com.example.domain.usecase

import com.example.domain.entity.Note
import com.example.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    operator fun invoke(query: String): Flow<List<Note>> {
        return repository.searchNotes(query)
    }
}