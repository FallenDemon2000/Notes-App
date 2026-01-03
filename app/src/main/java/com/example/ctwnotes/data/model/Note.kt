package com.example.ctwnotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String = "",
    val content: String = "",
    val date: String = "",
    val color: Long,
    val areOptionsRevealed: Boolean = false,
)
