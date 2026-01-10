package com.example.notesapp.data.local

import com.example.notesapp.data.model.Note
import com.example.notesapp.data.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class LocalNoteRepository(
    private val noteDao: NoteDao,
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> =
        noteDao.getAllNotes()

    override fun getNoteById(id: Int): Flow<Note?> =
        noteDao.getNoteById(id)

    override suspend fun addNote(note: Note) =
        noteDao.addNote(note)

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}
