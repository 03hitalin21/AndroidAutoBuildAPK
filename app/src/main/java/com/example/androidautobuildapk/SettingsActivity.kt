package com.example.androidautobuildapk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        title = getString(R.string.menu_settings)

        val switch = findViewById<MaterialSwitch>(R.id.themeSwitch)
        switch.isChecked = ThemePreferences.isDarkModeEnabled(this)
        switch.setOnCheckedChangeListener { _, isChecked ->
            ThemePreferences.setDarkModeEnabled(this, isChecked)
        }
    }
}
