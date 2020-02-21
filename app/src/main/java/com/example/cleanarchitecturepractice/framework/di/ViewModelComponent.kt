package com.example.cleanarchitecturepractice.framework.di

import com.example.cleanarchitecturepractice.presentation.ListNote.ListViewModel
import com.example.cleanarchitecturepractice.presentation.AddEditNote.NoteViewModel
import dagger.Component

@Component(
    modules = [ApplicationModule::class,
        RepositoryModule::class, UseCaseModule::class]
)
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}