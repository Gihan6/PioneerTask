package com.qdreamcaller.creativemindstask.ui.base

import android.app.Application
import androidx.work.Configuration
import com.qdreamcaller.creativemindstask.BuildConfig
import com.qdreamcaller.creativemindstask.di.appModules
import com.qdreamcaller.creativemindstask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

import org.koin.core.context.startKoin

class App : Application() , Configuration.Provider{

    override fun onCreate() {
        super.onCreate()

        // Unique initialization of Dependency Injection library to allow the use of application context
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(appModules, viewModelModule))
        }


    }

    override fun getWorkManagerConfiguration(): Configuration {
        return if (BuildConfig.DEBUG) {
            Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.DEBUG)
                .build()
        } else {
            Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.ERROR)
                .build()
        }    }
}