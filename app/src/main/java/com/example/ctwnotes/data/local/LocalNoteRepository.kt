package com.example.ctwnotes.data.local

import com.example.ctwnotes.data.model.Note
import com.example.ctwnotes.data.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class LocalNoteRepository(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override fun getNoteById(id: Int): Flow<Note?> {
        return noteDao.getNoteById(id)
    }

    override suspend fun addNote(note: Note)  {
        noteDao.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}