package com.example.stepik_project_notepad.data

import com.example.stepik_project_notepad.domain.ContentItem
import com.example.stepik_project_notepad.domain.Note
import kotlinx.serialization.json.Json

fun Note.toDbModel(): NoteDbModel {
    val contentAsString = Json.encodeToString(content.toContentItemsDbModel())
    return NoteDbModel(id, title, contentAsString, updatedAt, isPinned)
}

fun List<ContentItem>.toContentItemsDbModel(): List<ContentItemDbModel> {
    return map { contentItem ->
        when(contentItem) {
            is ContentItem.Image -> {
                ContentItemDbModel.Image(url = contentItem.url)
            }
            is ContentItem.Text -> {
                ContentItemDbModel.Text(content = contentItem.content)
            }
        }
    }
}

fun List<ContentItemDbModel>.toContentItems(): List<ContentItem> {
    return map { contentItem ->
        when(contentItem) {
            is ContentItemDbModel.Image -> {
                ContentItem.Image(url = contentItem.url)
            }
            is ContentItemDbModel.Text -> {
                ContentItem.Text(content = contentItem.content)
            }
        }
    }
}

fun NoteDbModel.toEntity(): Note {
    val contentItemDbModels = Json.decodeFromString<List<ContentItemDbModel>>(content)
    return Note(id, title, contentItemDbModels.toContentItems(), updatedAt, isPinned)
}

fun List<NoteDbModel>.toEntities(): List<Note> {
    return this.map { it.toEntity() }
}