package com.example.notesapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.PastelGradientTrack
import com.example.notesapp.PastelThumb
import com.example.notesapp.data.model.Note
import com.example.notesapp.pastelColorAt
import com.example.notesapp.presentation.ui.theme.NotesAppTheme
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.random.Random

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ColorPickerBottomSheet(
    onFinishRequest: (Note) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val sliderState = rememberSliderState(value = Random.nextFloat())
    var noteTitle by rememberSaveable { mutableStateOf("") }
    var noteDescription by rememberSaveable { mutableStateOf("") }

    val onTitleTextChanged: (String) -> Unit = { noteTitle = it }
    val onDescriptionTextChanged: (String) -> Unit = { noteDescription = it }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp, 4.dp)
                .align(Alignment.End),
        ) {
            OutlinedTextField(
                value = noteTitle,
                onValueChange = onTitleTextChanged,
                label = { Text("Title") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = noteDescription,
                onValueChange = onDescriptionTextChanged,
                label = { Text("Description") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                modifier = Modifier,
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
                        if (noteTitle.isNotEmpty() || noteDescription.isNotEmpty()) {
                            onFinishRequest(
                                Note(
                                    title = noteTitle,
                                    description = noteDescription,
                                    date = LocalDateTime.now().toString(),
                                    color = pastelColorAt(sliderState.value).value.toLong(),
                                ),
                            )
                        }

                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (sheetState.isVisible.not()) onDismissRequest()
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text("Add Note")
                }
            }
        }
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("detekt:UnusedPrivateMember")
private fun ColorPickerBottomSheetPreview() {
    NotesAppTheme {
        ColorPickerBottomSheet(
            onFinishRequest = {},
            onDismissRequest = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
