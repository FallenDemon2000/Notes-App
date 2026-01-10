package com.example.notesapp.presentation.uistate

import com.example.notesapp.data.model.Note

data class NotesUiState(
    val notes: List<Note> = emptyList(),
)
