package com.example.androidautobuildapk

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_page)
        title = getString(R.string.menu_about)

        val s1 = findViewById<TextView>(R.id.section1Title)
        val s2 = findViewById<TextView>(R.id.section2Title)
        val s3 = findViewById<TextView>(R.id.section3Title)

        s1.text = getString(R.string.about_section1_title)
        findViewById<TextView>(R.id.section1Body).text = getString(R.string.about_section1_body)
        s2.text = getString(R.string.about_section2_title)
        findViewById<TextView>(R.id.section2Body).text = getString(R.string.about_section2_body)
        s3.text = getString(R.string.about_section3_title)
        findViewById<TextView>(R.id.section3Body).text = getString(R.string.about_section3_body)
        findViewById<TextView>(R.id.highlightNote).text = getString(R.string.about_note)

        findViewById<com.google.android.material.button.MaterialButton>(R.id.tocButton1).apply {
            text = "1. " + s1.text
            setOnClickListener { findViewById<View>(R.id.section1Card).requestFocus() }
        }
        findViewById<com.google.android.material.button.MaterialButton>(R.id.tocButton2).apply {
            text = "2. " + s2.text
            setOnClickListener { findViewById<View>(R.id.section2Card).requestFocus() }
        }
        findViewById<com.google.android.material.button.MaterialButton>(R.id.tocButton3).apply {
            text = "3. " + s3.text
            setOnClickListener { findViewById<View>(R.id.section3Card).requestFocus() }
        }
    }
}
