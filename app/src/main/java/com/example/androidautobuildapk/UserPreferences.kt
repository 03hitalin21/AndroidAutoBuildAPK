package com.example.androidautobuildapk

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object UserPreferences {
    private const val PREFS_NAME = "app_prefs"
    private const val KEY_THEME_MODE = "theme_mode"
    private const val KEY_FONT_SCALE = "font_scale"
    private const val KEY_LANGUAGE = "language"
    private const val KEY_BOOKMARKS = "bookmarks"
    private const val KEY_FAVORITES = "favorites"

    const val THEME_SYSTEM = "system"
    const val THEME_LIGHT = "light"
    const val THEME_DARK = "dark"

    fun getThemeMode(context: Context): String = prefs(context).getString(KEY_THEME_MODE, THEME_SYSTEM) ?: THEME_SYSTEM
    fun setThemeMode(context: Context, value: String) { prefs(context).edit().putString(KEY_THEME_MODE, value).apply(); applyTheme(context) }

    fun applyTheme(context: Context) {
        val mode = when (getThemeMode(context)) {
            THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    fun getFontScale(context: Context): Float = prefs(context).getFloat(KEY_FONT_SCALE, 1.0f)
    fun setFontScale(context: Context, scale: Float) { prefs(context).edit().putFloat(KEY_FONT_SCALE, scale).apply() }

    fun getLanguage(context: Context): String {
        return prefs(context).getString(KEY_LANGUAGE, "system") ?: "system"
    }
    fun setLanguage(context: Context, code: String) { prefs(context).edit().putString(KEY_LANGUAGE, code).apply() }

    fun toggleBookmark(context: Context, article: String): Boolean {
        val set = prefs(context).getStringSet(KEY_BOOKMARKS, emptySet())!!.toMutableSet()
        val added = if (set.contains(article)) { set.remove(article); false } else { set.add(article); true }
        prefs(context).edit().putStringSet(KEY_BOOKMARKS, set).apply(); return added
    }
    fun toggleFavorite(context: Context, article: String): Boolean {
        val set = prefs(context).getStringSet(KEY_FAVORITES, emptySet())!!.toMutableSet()
        val added = if (set.contains(article)) { set.remove(article); false } else { set.add(article); true }
        prefs(context).edit().putStringSet(KEY_FAVORITES, set).apply(); return added
    }
    fun getBookmarks(context: Context): Set<String> = prefs(context).getStringSet(KEY_BOOKMARKS, emptySet()) ?: emptySet()
    fun getFavorites(context: Context): Set<String> = prefs(context).getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
    fun isBookmarked(context: Context, article: String): Boolean = getBookmarks(context).contains(article)
    fun isFavorited(context: Context, article: String): Boolean = getFavorites(context).contains(article)

    private fun prefs(context: Context) = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}
