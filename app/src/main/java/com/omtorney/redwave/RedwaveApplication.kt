package com.omtorney.redwave

import android.app.Application
import com.omtorney.redwave.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RedwaveApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RedwaveApplication)
            modules(appModule)
        }
    }
}
