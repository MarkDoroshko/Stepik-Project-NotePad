package com.example.data.local.mapper

import com.example.data.local.model.ContentItemDbModel
import com.example.data.local.model.ContentType
import com.example.data.local.model.NoteDbModel
import com.example.data.local.model.NoteWithContentDbModel
import com.example.domain.entity.ContentItem
import com.example.domain.entity.Note

fun Note.toDbModel(): NoteDbModel {
    return NoteDbModel(id, title, updatedAt, isPinned)
}

fun List<ContentItem>.toContentItemsDbModel(noteId: Int): List<ContentItemDbModel> {
    return mapIndexed { index, contentItem ->
        when (contentItem) {
            is ContentItem.Image -> {
                ContentItemDbModel(
                    noteId = noteId,
                    contentType = ContentType.IMAGE,
                    content = contentItem.url,
                    order = index
                )
            }

            is ContentItem.Text -> {
                ContentItemDbModel(
                    noteId = noteId,
                    contentType = ContentType.TEXT,
                    content = contentItem.content,
                    order = index
                )
            }
        }
    }
}

fun List<ContentItemDbModel>.toContentItems(): List<ContentItem> {
    return map { contentItem ->
        when (contentItem.contentType) {
            ContentType.TEXT -> {
                ContentItem.Text(content = contentItem.content)
            }

            ContentType.IMAGE -> {
                ContentItem.Image(url = contentItem.content)
            }
        }
    }
}

fun NoteWithContentDbModel.toEntity(): Note {
    return Note(
        id = noteDbModel.id,
        title = noteDbModel.title,
        content = content.toContentItems(),
        updatedAt = noteDbModel.updatedAt,
        isPinned = noteDbModel.isPinned
    )
}

fun List<NoteWithContentDbModel>.toEntities(): List<Note> {
    return this.map { it.toEntity() }
}