package com.example.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.NotesDao
import com.example.data.local.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NotesDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = NotesDatabase::class.java,
            name = "notes.db"
        ).fallbackToDestructiveMigration(dropAllTables = true).build()
    }

    @Singleton
    @Provides
    fun provideNotesDao(
        database: NotesDatabase
    ): NotesDao {
        return database.notesDao()
    }
}