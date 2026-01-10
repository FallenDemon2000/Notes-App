package com.example.notesapp.presentation.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Replay
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.notesapp.R

enum class NoteAction(
    val icon: ImageVector,
    val tint: Color,
    @StringRes val descriptionID: Int,
) {
    DELETE(
        icon = Icons.Filled.Delete,
        descriptionID = R.string.delete_icon,
        tint = Color.Red,
    ),
    RESTORE(
        icon = Icons.Filled.Replay,
        descriptionID = R.string.restore_icon,
        tint = Color.Green,
    ),
}
