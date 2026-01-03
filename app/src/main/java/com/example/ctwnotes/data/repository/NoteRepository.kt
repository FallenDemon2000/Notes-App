package com.example.ctwnotes.data.repository

import com.example.ctwnotes.data.model.Note
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

interface NoteRepository : KoinComponent {
    fun getAllNotes(): Flow<List<Note>>
    fun getNoteById(id: Int): Flow<Note?>
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
}
