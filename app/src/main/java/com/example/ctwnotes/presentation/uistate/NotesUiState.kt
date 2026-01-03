package com.example.ctwnotes.presentation.uistate

import com.example.ctwnotes.data.model.Note
import kotlin.random.Random

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val sliderPosition: Float = Random.nextFloat(),
    val noteTitle: String = "",
    val noteDescription: String = "",
)