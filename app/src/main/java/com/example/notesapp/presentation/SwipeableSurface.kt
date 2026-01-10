package com.example.notesapp.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.notesapp.presentation.model.NoteAction
import com.example.notesapp.presentation.ui.theme.NotesAppTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Suppress("detekt:LongParameterList")
@Composable
fun SwipeableSurface(
    modifier: Modifier = Modifier,
    actions: Map<NoteAction, () -> Unit>,
    content: @Composable () -> Unit,
) {
    var isRevealed by remember { mutableStateOf(false) }
    var contextMenuWidth by remember { mutableFloatStateOf(0f) }
    val offset = remember { Animatable(initialValue = 0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = isRevealed, key2 = contextMenuWidth) {
        offset.animateTo(if (isRevealed) contextMenuWidth else 0f)
    }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .onSizeChanged { contextMenuWidth = -it.width.toFloat() },
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            actions.forEach { (action, onClickAction) ->
                ActionIconButton(
                    action = action,
                    onClick = { onClickAction() },
                    modifier = Modifier.fillMaxHeight(),
                )
            }
        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offset.value.roundToInt(), 0) }
                .pointerInput(true) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newOffset = offset.value + dragAmount
                                offset.snapTo(newOffset.coerceIn(contextMenuWidth, 0f))
                            }
                        },
                        onDragEnd = {
                            when {
                                offset.value < contextMenuWidth / 2f -> {
                                    scope.launch {
                                        offset.animateTo(contextMenuWidth)
                                    }
                                }

                                offset.value >= contextMenuWidth / 2f -> {
                                    scope.launch {
                                        offset.animateTo(0f)
                                    }
                                }
                            }
                        },
                    )
                },
        ) {
            content()
        }
    }
}

@Composable
private fun ActionIconButton(
    action: NoteAction,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = action.icon,
            contentDescription = stringResource(action.descriptionID),
            tint = action.tint,
        )
    }
}

@Preview
@Composable
@Suppress("detekt:UnusedPrivateMember")
private fun SwipeableSurfacePreview() {
    val actions = listOf(NoteAction.RESTORE, NoteAction.DELETE).associateWith { {} }
    NotesAppTheme {
        SwipeableSurface(actions = actions) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(5) {
                    NoteCard(
                        title = "Note Title",
                        description = "Note Description",
                        color = Color.Cyan.value.toLong(),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}
