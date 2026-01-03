package com.example.ctwnotes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ctwnotes.PastelGradientTrack
import com.example.ctwnotes.PastelThumb
import com.example.ctwnotes.data.model.Note
import com.example.ctwnotes.pastelColorAt
import com.example.ctwnotes.presentation.viewmodel.NotesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import kotlin.random.Random

@Suppress("detekt:LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTWNotesApp() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val sliderState = rememberSliderState(value = Random.nextFloat())

    val viewModel = koinViewModel<NotesViewModel>()
    val notesUiState = viewModel.uiState.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            val btnText = "Add Note"
            ExtendedFloatingActionButton(
                text = { Text(btnText) },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = btnText,
                    )
                },
                onClick = {
                    showBottomSheet = true
                    sliderState.value = Random.nextFloat()
                },
            )
        },
    ) { innerPadding ->
        NoteLazyList(
            noteList = notesUiState.notes,
            onRefresh = { viewModel.getAllNotes() },
            modifier = Modifier
                .padding(16.dp) // manual side padding cause innerPadding doesn't have it
                .padding(innerPadding)
                .fillMaxSize(),
        )
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
            ) {
                val insideSheetMod = Modifier
                    .padding(16.dp, 4.dp)
                    .align(Alignment.End)
                val noteTitleLabel = "Title"
                val noteDescriptionLabel = "Description"
                OutlinedTextField(
                    value = notesUiState.noteTitle,
                    onValueChange = { viewModel.updateTitle(it) },
                    label = { Text(noteTitleLabel) },
                    shape = RoundedCornerShape(12.dp),
                    modifier = insideSheetMod.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = notesUiState.noteDescription,
                    onValueChange = { viewModel.updateDescription(it) },
                    label = { Text(noteDescriptionLabel) },
                    shape = RoundedCornerShape(12.dp),
                    modifier = insideSheetMod.fillMaxWidth(),
                )
                // Text("Slider State: ${sliderState.value}")
                Row(
                    modifier = insideSheetMod,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Slider(
                        state = sliderState,
                        track = { sliderState ->
                            PastelGradientTrack(sliderState)
                        },
                        thumb = { sliderState ->
                            PastelThumb(sliderState)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                    )
                    Button(
                        onClick = {
                            val newNote = Note(
                                // id = noteList.size, // Python server does numbers the notes
                                title = notesUiState.noteTitle,
                                content = notesUiState.noteDescription,
                                date = LocalDateTime.now().toString(),
                                color = pastelColorAt(sliderState.value).value.toLong(),
                            )
                            // noteList.add(newNote)
                            viewModel.addNote(newNote)
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) showBottomSheet = false
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        // modifier = insideSheetMod,
                    ) {
                        Text("Add Note")
                    }
                }
            }
        }
    }
}
