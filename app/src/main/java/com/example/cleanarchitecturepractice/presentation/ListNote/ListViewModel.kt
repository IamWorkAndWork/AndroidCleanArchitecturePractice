package com.example.cleanarchitecturepractice.presentation.ListNote

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecturepractice.framework.UseCases
import com.example.cleanarchitecturepractice.framework.di.ApplicationModule
import com.example.cleanarchitecturepractice.framework.di.DaggerViewModelComponent
import com.example.core.data.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val notes = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            val noteList = useCases.getAllNotes()

            noteList.forEach {
                val wordCount = useCases.getWordCount.invoke(it)
                it.wordCount = wordCount
            }

            notes.postValue(noteList)
        }
    }

}