package com.example.stepik_project_notepad.data

import com.example.stepik_project_notepad.domain.Note

fun Note.toDbModel(): NoteDbModel {
    return NoteDbModel(id, title, content, updatedAt, isPinned)
}

fun NoteDbModel.toEntity(): Note {
    return Note(id, title, content, updatedAt, isPinned)
}