package com.example.stepik_project_notepad.data

import kotlinx.serialization.Serializable

@Serializable
sealed interface ContentItemDbModel {
    @Serializable
    data class Text(val content: String) : ContentItemDbModel

    @Serializable
    data class Image(val url: String) : ContentItemDbModel
}