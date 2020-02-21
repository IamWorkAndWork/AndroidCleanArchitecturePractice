package com.example.core.repository

import com.example.core.data.Note

class NoteRepository(private val dataSource: NoteDataSource) {

    suspend fun addNote(note: Note) = dataSource.add(note)

    suspend fun getNote(id: Long) = dataSource.get(id)

    suspend fun getAllNotes(): List<Note> {
        return dataSource.getAll()
    }

    suspend fun removeNote(note: Note) {
        return dataSource.remove(note)
    }

}