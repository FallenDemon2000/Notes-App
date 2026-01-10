package com.example.notesapp.data.remote

import com.example.notesapp.data.model.Note
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NoteApiService {

    @GET("notes")
    suspend fun getAllNotes(): List<Note>

    @GET("notes/{id}")
    suspend fun getNoteById(
        @Path("id") id: Int,
    ): Note?

    @POST("notes")
    suspend fun addNote(
        @Body note: Note,
    ): Note

    @PUT("notes/{id}")
    suspend fun updateNote(
        @Path("id") id: Int,
        @Body note: Note,
    ): Note

    @DELETE("notes/{id}")
    suspend fun deleteNote(
        @Path("id") id: Int,
    )
}
