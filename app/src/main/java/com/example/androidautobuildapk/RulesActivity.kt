package com.example.androidautobuildapk

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_page)
        title = getString(R.string.menu_rules)

        findViewById<TextView>(R.id.tocContent).text = getString(R.string.rules_toc)
        findViewById<TextView>(R.id.section1Title).text = getString(R.string.rules_section1_title)
        findViewById<TextView>(R.id.section1Body).text = getString(R.string.rules_section1_body)
        findViewById<TextView>(R.id.section2Title).text = getString(R.string.rules_section2_title)
        findViewById<TextView>(R.id.section2Body).text = getString(R.string.rules_section2_body)
        findViewById<TextView>(R.id.section3Title).text = getString(R.string.rules_section3_title)
        findViewById<TextView>(R.id.section3Body).text = getString(R.string.rules_section3_body)
    }
}
