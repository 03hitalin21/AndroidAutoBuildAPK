package com.example.androidautobuildapk

import android.app.Application

class HokmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UserPreferences.applyTheme(this)
        if (UserPreferences.getLanguage(this) == "system") {
            UserPreferences.setLanguage(this, java.util.Locale.getDefault().language)
        }
    }
}
