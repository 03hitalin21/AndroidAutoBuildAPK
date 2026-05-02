package com.example.androidautobuildapk

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class SettingsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        title = getString(R.string.menu_settings)

        setupThemeSpinner()
        setupFontSpinner()
        setupLanguageSpinner()
    }

    private fun setupThemeSpinner() {
        val spinner = findViewById<Spinner>(R.id.themeSpinner)
        val items = listOf("System", "Light", "Dark")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.setSelection(listOf(UserPreferences.THEME_SYSTEM, UserPreferences.THEME_LIGHT, UserPreferences.THEME_DARK).indexOf(UserPreferences.getThemeMode(this)).coerceAtLeast(0))
        spinner.setOnItemSelectedListener(SimpleItemSelectedListener { pos ->
            val v = listOf(UserPreferences.THEME_SYSTEM, UserPreferences.THEME_LIGHT, UserPreferences.THEME_DARK)[pos]
            if (v != UserPreferences.getThemeMode(this)) UserPreferences.setThemeMode(this, v)
        })
    }

    private fun setupFontSpinner() {
        val spinner = findViewById<Spinner>(R.id.fontSpinner)
        val values = listOf(0.9f, 1.0f, 1.1f)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("Small", "Normal", "Large"))
        spinner.setSelection(values.indexOfFirst { kotlin.math.abs(it - UserPreferences.getFontScale(this)) < 0.01f }.coerceAtLeast(1))
        spinner.setOnItemSelectedListener(SimpleItemSelectedListener { pos ->
            val v = values[pos]
            if (kotlin.math.abs(v - UserPreferences.getFontScale(this)) > 0.01f) { UserPreferences.setFontScale(this, v); recreate() }
        })
    }

    private fun setupLanguageSpinner() {
        val spinner = findViewById<Spinner>(R.id.languageSpinner)
        val codes = listOf("system", "en", "fa")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("System default", "English", "Persian"))
        spinner.setSelection(codes.indexOf(UserPreferences.getLanguage(this)).coerceAtLeast(0))
        spinner.setOnItemSelectedListener(SimpleItemSelectedListener { pos ->
            val v = codes[pos]
            if (v != UserPreferences.getLanguage(this)) { UserPreferences.setLanguage(this, v); recreate() }
        })
    }
}
