package com.example.ctwnotes.presentation.viewmodel

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctwnotes.R
import com.example.ctwnotes.data.model.Note
import com.example.ctwnotes.data.repository.NoteRepository
import com.example.ctwnotes.presentation.uistate.NotesUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class NotesViewModel(
    private val noteRepository: NoteRepository,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState

    fun updateTitle(title: String) {
        _uiState.update {
            it.copy( noteTitle = title )
        }
    }

    fun updateDescription(description: String) {
        _uiState.update {
            it.copy( noteDescription = description )
        }
    }

    init {
        getAllNotes()
    }

    fun getAllNotes() {
        viewModelScope.launch {
            noteRepository.getAllNotes().collect { repoNotes ->
                _uiState.update { state ->
                    state.copy( notes = repoNotes )
                }
            }
        }
    }

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            noteRepository.getNoteById(id).collect { repoNote ->
                _uiState.update { state ->
                    state.copy(  )
                }
            }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepository.addNote(note)
            delay(250L)
            getAllNotes()
            
            _uiState.update { state -> 
                state.copy(
                    noteTitle = "",
                    noteDescription = "",
                )
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            delay(250L)
            getAllNotes()
        }
    }
}