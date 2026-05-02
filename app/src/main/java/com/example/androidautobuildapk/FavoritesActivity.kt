package com.example.androidautobuildapk

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class FavoritesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_list)
        title = getString(R.string.menu_favorites)
        findViewById<TextView>(R.id.savedTitle).text = getString(R.string.menu_favorites)
        bind(UserPreferences.getFavorites(this).sorted())
    }

    private fun bind(items: List<String>) {
        findViewById<TextView>(R.id.emptyState).visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
        findViewById<ListView>(R.id.savedList).adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
    }
}
