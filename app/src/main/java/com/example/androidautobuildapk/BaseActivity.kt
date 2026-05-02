package com.example.androidautobuildapk

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

open class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context) {
        val languageCode = UserPreferences.getLanguage(newBase)
        val locale = if (languageCode == "system") Locale.getDefault() else Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(newBase.resources.configuration)
        config.setLocale(locale)
        config.fontScale = UserPreferences.getFontScale(newBase)
        val context = newBase.createConfigurationContext(config)
        super.attachBaseContext(context)
    }
}
