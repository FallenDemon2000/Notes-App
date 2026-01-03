package com.example.ctwnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctwnotes.presentation.CTWNotesApp
import com.example.ctwnotes.presentation.NoteCard
import com.example.ctwnotes.presentation.ui.theme.CTWNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CTWNotesTheme {
                CTWNotesApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    CTWNotesTheme {
        NoteCard()
    }
}

@Preview(showBackground = true)
@Composable
fun NoteCardAppPreview() {
    CTWNotesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
            NoteCard(
                title = "Title Test",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            )
        }
    }
}