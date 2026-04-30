package com.example.androidautobuildapk

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.aboutButton).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        findViewById<Button>(R.id.settingsButton).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<View>(R.id.rulesButton).setOnClickListener {
            startActivity(Intent(this, RulesActivity::class.java))
        }

        findViewById<View>(R.id.termsButton).setOnClickListener {
            startActivity(Intent(this, TermsActivity::class.java))
        }

        findViewById<View>(R.id.historyButton).setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        findViewById<View>(R.id.triviaButton).setOnClickListener {
            startActivity(Intent(this, TriviaActivity::class.java))
        }
    }
}
