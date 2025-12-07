package com.example.stepik_project_notepad.domain

sealed interface ContentItem {
    data class Text(val content: String) : ContentItem

    data class Image(val url: String) : ContentItem
}