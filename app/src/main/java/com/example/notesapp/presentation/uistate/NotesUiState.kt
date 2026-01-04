package com.example.notesapp.presentation.uistate

import com.example.notesapp.data.model.Note
import kotlin.random.Random

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val sliderPosition: Float = Random.nextFloat(),
    val noteTitle: String = "",
    val noteDescription: String = "",
)
