package com.example.androidautobuildapk

import android.app.Application

class HokmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ThemePreferences.applySavedTheme(this)
    }
}
