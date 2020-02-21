package com.example.cleanarchitecturepractice.framework.di

import com.example.cleanarchitecturepractice.framework.UseCases
import com.example.core.repository.NoteRepository
import com.example.core.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getUseCases(repository: NoteRepository): UseCases {
        return UseCases(
            AddNote(repository),
            GetAllNotes(repository),
            GetNote(repository),
            RemoveNote(repository),
            GetWordCount()
        )
    }

}