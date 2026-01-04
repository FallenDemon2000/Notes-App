package com.example.notesapp

import android.app.Application
import com.example.notesapp.di.localStoreModule
import com.example.notesapp.di.remoteStoreModule
import com.example.notesapp.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteApplication)
            modules(remoteStoreModule + localStoreModule + uiModule)
        }
    }
}
