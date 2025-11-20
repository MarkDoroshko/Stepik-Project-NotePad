package com.example.stepik_project_notepad.domain

import kotlinx.coroutines.flow.Flow

class SearchNotesUseCase {
    operator fun invoke(query: String): Flow<List<Note>> {
        TODO()
    }
}