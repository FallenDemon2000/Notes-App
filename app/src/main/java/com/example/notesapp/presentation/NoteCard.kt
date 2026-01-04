package com.example.notesapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.presentation.ui.theme.NotesAppTheme

@Composable
fun NoteCard(
    title: String,
    description: String,
    color: Long,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(color.toULong())),
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = modifier.padding(16.dp),
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = modifier.padding(8.dp))
            Text(
                text = description,
            )
        }
    }
}

@Preview
@Composable
private fun NoteCardPreview() {
    NotesAppTheme {
        NoteCard(
            title = "Note Title",
            description = "Note Description",
            color = Color.Yellow.value.toLong(),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun DeletedNoteCardPreview() {
    NotesAppTheme(darkTheme = true) {
        NoteCard(
            title = "Deleted Note Title",
            description = "Deleted Note Description",
            color = Color.Cyan.value.toLong(),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
