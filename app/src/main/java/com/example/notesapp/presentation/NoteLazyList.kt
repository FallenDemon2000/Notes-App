package com.example.notesapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.data.model.Note
import com.example.notesapp.presentation.model.NoteAction
import com.example.notesapp.presentation.ui.theme.NotesAppTheme
import com.example.notesapp.randomBGColor

@Composable
fun NoteLazyList(
    noteList: List<Note>,
    onDeleteNote: (Note) -> Unit,
    modifier: Modifier = Modifier,
    onRestoreNote: (Note) -> Unit = {},
    onRefresh: () -> Unit = {},
) {
    PullToRefreshBox(
        isRefreshing = false,
        onRefresh = onRefresh,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier,
        ) {
            items(
                items = noteList,
                key = { it.id!! },
            ) { note ->
                val actions = mutableMapOf(NoteAction.DELETE to { onDeleteNote(note) })
                if (note.isDeleted) actions[NoteAction.RESTORE] = { onRestoreNote(note) }
                SwipeableSurface(
                    actions = actions,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    NoteCard(
                        title = note.title,
                        description = note.description,
                        color = note.color,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
@Suppress("detekt:UnusedPrivateMember")
private fun NoteCardPreview() {
    NotesAppTheme {
        NoteLazyList(
            noteList = List(5) {
                Note(
                    id = it,
                    title = "Note Title",
                    description = "Note Description",
                    date = "yesterday",
                    color = randomBGColor().value.toLong(),
                )
            },
            onDeleteNote = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
