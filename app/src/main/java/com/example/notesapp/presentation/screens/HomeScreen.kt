package com.example.notesapp.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notesapp.R
import com.example.notesapp.data.model.Note
import com.example.notesapp.presentation.ColorPickerBottomSheet
import com.example.notesapp.presentation.NoteLazyList
import com.example.notesapp.presentation.viewmodel.NotesViewModel
import org.koin.androidx.compose.koinViewModel

const val HOME_SCREEN_ROUTE = "home-screen"

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen() {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    val viewModel = koinViewModel<NotesViewModel>()
    val notesUiState = viewModel.uiState.collectAsState().value

    val onDeleteNote: (Note) -> Unit = { viewModel.updateNote(it.copy(isDeleted = true)) }
    val onRestoreNote: (Note) -> Unit = { viewModel.updateNote(it.copy(isDeleted = false)) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_icon),
                )
            }
        },
    ) { innerPadding ->
        NoteLazyList(
            noteList = notesUiState.notes,
            onDeleteNote = onDeleteNote,
            onRestoreNote = onRestoreNote,
            onRefresh = { viewModel.getAllNotes() },
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
        )
        if (showBottomSheet) {
            ColorPickerBottomSheet(
                onFinishRequest = { viewModel.addNote(it) },
                onDismissRequest = { showBottomSheet = false },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
