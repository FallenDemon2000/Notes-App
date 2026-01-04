package com.example.ctwnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctwnotes.presentation.NoteCard
import com.example.ctwnotes.presentation.NotesApp
import com.example.ctwnotes.presentation.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                NotesApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    NotesAppTheme {
        NoteCard()
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardAppPreview() {
    NotesAppTheme {
        NoteCard(
            title = "Title Test",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut" +
                "aliquip ex ea commodo consequat.",
        )
    }
}
