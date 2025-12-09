package com.example.stepik_project_notepad.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}