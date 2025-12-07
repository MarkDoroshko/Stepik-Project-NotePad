package com.example.stepik_project_notepad.domain

data class Note(
    val id: Int,
    val title: String,
    val content: List<ContentItem>,
    val updatedAt: Long,
    val isPinned: Boolean
)
