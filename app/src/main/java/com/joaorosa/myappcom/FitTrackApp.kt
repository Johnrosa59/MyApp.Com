package com.joaorosa.myappcom

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitTrackApp : Application() {
    override fun onCreate(): Unit {
        super.onCreate()
    }
}
