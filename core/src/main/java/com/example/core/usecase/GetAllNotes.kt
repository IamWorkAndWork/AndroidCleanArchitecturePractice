package com.example.core.usecase

import com.example.core.data.Note
import com.example.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(): List<Note> {
        return noteRepository.getAllNotes()
    }
}