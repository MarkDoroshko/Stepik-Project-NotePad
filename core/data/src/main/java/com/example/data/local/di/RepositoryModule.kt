package com.example.data.local.di

import com.example.data.repository.NotesRepositoryImpl
import com.example.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindNotesRepository(
        impl: NotesRepositoryImpl
    ): NotesRepository
}