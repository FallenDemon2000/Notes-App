package com.example.ctwnotes.di

import androidx.room.Room
import com.example.ctwnotes.data.local.LocalNoteRepository
import com.example.ctwnotes.data.local.NoteDao
import com.example.ctwnotes.data.local.NoteDatabase
import com.example.ctwnotes.data.remote.NoteApiService
import com.example.ctwnotes.data.repository.NoteRepository
import com.example.ctwnotes.data.repository.RemoteNoteRepository
import com.example.ctwnotes.presentation.viewmodel.NotesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteStoreModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<NoteApiService> { get<Retrofit>().create(NoteApiService::class.java) }
    single<NoteRepository> {
        RemoteNoteRepository(
            apiService = get<NoteApiService>()
        )
    }
}

val localStoreModule = module {
    single<NoteDatabase> {
        Room.databaseBuilder(
            androidContext(),
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }
    single<NoteDao> { get<NoteDatabase>().noteDao()}
    single<NoteRepository> { LocalNoteRepository(get<NoteDao>()) }
}

val uiModule = module {
    viewModel { NotesViewModel(get()) }
    //viewModel { NoteDetailViewModel(get(), get()) }
    //viewModel { ThemeViewModel(get()) }
}