package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.NotesDao
import com.example.data.local.model.ContentItemDbModel
import com.example.data.local.model.NoteDbModel

@Database(
    entities = [NoteDbModel::class, ContentItemDbModel::class],
    version = 2,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}