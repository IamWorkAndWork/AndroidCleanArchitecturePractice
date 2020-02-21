package com.example.cleanarchitecturepractice.framework.di

import android.app.Application
import com.example.cleanarchitecturepractice.framework.RoomNoteDataSource
import com.example.core.repository.NoteDataSource
import com.example.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application): NoteRepository {
        return NoteRepository(RoomNoteDataSource(app))
    }

}