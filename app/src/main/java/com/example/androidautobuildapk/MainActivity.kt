package com.example.androidautobuildapk

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.rulesCard).setOnClickListener { startActivity(Intent(this, RulesActivity::class.java)) }
        findViewById<View>(R.id.termsCard).setOnClickListener { startActivity(Intent(this, TermsActivity::class.java)) }
        findViewById<View>(R.id.historyCard).setOnClickListener { startActivity(Intent(this, HistoryActivity::class.java)) }
        findViewById<View>(R.id.triviaCard).setOnClickListener { startActivity(Intent(this, TriviaActivity::class.java)) }

        findViewById<View>(R.id.homeFooterButton).setOnClickListener {
            Toast.makeText(this, getString(R.string.already_on_home), Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.bookmarksFooterButton).setOnClickListener {
            startActivity(Intent(this, BookmarksActivity::class.java))
        }
        findViewById<View>(R.id.favoritesFooterButton).setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
        findViewById<View>(R.id.settingsFooterButton).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
