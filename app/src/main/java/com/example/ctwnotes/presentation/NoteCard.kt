package com.example.ctwnotes.presentation

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
import androidx.compose.ui.unit.dp
import com.example.ctwnotes.randomBGColor

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    title: String = "Note Title",
    content: String = "Note Content",
    color: Long = randomBGColor().value.toLong(),
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
                text = content,
            )
        }
    }
}
