package com.example.ctwnotes

import android.app.Application
import com.example.ctwnotes.di.localStoreModule
import com.example.ctwnotes.di.remoteStoreModule
import com.example.ctwnotes.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteApplication)
            modules(remoteStoreModule + localStoreModule + uiModule)
        }
    }
}
