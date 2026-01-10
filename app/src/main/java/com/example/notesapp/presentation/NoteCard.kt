package com.example.notesapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
@Suppress("detekt:UnusedPrivateMember")
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
