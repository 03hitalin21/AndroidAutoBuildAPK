package com.hokmencyclopedia.app

import android.app.Application
import com.hokmencyclopedia.app.core.preferences.UserPreferences

class HokmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UserPreferences.applyTheme(this)
        if (UserPreferences.getLanguage(this) == "system") {
            UserPreferences.setLanguage(this, java.util.Locale.getDefault().language)
        }
    }
}
