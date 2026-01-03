package com.example.ctwnotes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ctwnotes.ActionIconButton
import com.example.ctwnotes.SwipeToRevealDelete
import com.example.ctwnotes.data.model.Note
import com.example.ctwnotes.randomBGColor

@Composable
fun NoteLazyList(
    noteList: List<Note>,
    onRefresh: () -> Unit = {  },
    deleteNote: (Note) -> Unit,
    modifier: Modifier = Modifier,
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
                noteList,
                key = { it.id!! }
            ) { note ->
                SwipeableCardWithActions(
                    isRevealed = note.areOptionsRevealed,
                    actions = {
                        ActionIconButton(
                            onClick = {},
                            icon = Icons.Filled.Delete,
                            tint = Color.Red,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                ) {
                    NoteCard(
                        title = note.title,
                        content = note.content,
                        color = note.color ?: randomBGColor().value.toLong(),
                    )
                }
//                SwipeToRevealDelete(
//                    item = note,
//                    onDelete = {
//                        //noteList.remove(note)
//                        deleteNote(note)
//                    }
//                ) {
//                    NoteCard(
//                        title = note.title,
//                        content = note.content,
//                        color = note.color
//                    )
//                }
            }
        }
    }
}
