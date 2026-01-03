package com.example.ctwnotes.data.repository

import com.example.ctwnotes.data.model.Note
import com.example.ctwnotes.data.remote.NoteApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteNoteRepository(
    private val apiService: NoteApiService,
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> = flow {
        val notes = apiService.getAllNotes()
        emit(notes)
    }

    override fun getNoteById(id: Int): Flow<Note?> = flow {
        val note = apiService.getNoteById(id)
        emit(note)
    }

    override suspend fun addNote(note: Note) {
        apiService.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        if (note.id == null) throw IllegalArgumentException("No ID in note: $note")
        apiService.updateNote(note.id, note)
    }

    override suspend fun deleteNote(note: Note) {
        if (note.id == null) throw IllegalArgumentException("No ID in note: $note")
        apiService.deleteNote(note.id)
    }
}
