package com.datly.imagechallenge

import android.app.Application
import com.datly.imagechallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ImageChallengeApp: Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ImageChallengeApp)
            modules(appModule)
        }
    }
}
