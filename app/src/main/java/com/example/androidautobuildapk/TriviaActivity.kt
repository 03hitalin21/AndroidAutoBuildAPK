package com.example.androidautobuildapk

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TriviaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_page)
        title = getString(R.string.menu_trivia)

        val s1 = findViewById<TextView>(R.id.section1Title)
        val s2 = findViewById<TextView>(R.id.section2Title)
        val s3 = findViewById<TextView>(R.id.section3Title)
        val b1 = findViewById<TextView>(R.id.section1Body)
        val b2 = findViewById<TextView>(R.id.section2Body)
        val b3 = findViewById<TextView>(R.id.section3Body)

        s1.text = getString(R.string.trivia_section1_title)
        b1.text = getString(R.string.trivia_section1_body)
        s2.text = getString(R.string.trivia_section2_title)
        b2.text = getString(R.string.trivia_section2_body)
        s3.text = getString(R.string.trivia_section3_title)
        b3.text = getString(R.string.trivia_section3_body)
        findViewById<TextView>(R.id.highlightNote).text = getString(R.string.trivia_note)

        val icon1 = getSectionIcon(1)
        val icon2 = getSectionIcon(2)
        val icon3 = getSectionIcon(3)
        findViewById<ImageView>(R.id.section1Icon).setImageResource(icon1)
        findViewById<ImageView>(R.id.section2Icon).setImageResource(icon2)
        findViewById<ImageView>(R.id.section3Icon).setImageResource(icon3)

        findViewById<MaterialButton>(R.id.tocButton1).apply {
            text = "1. " + s1.text
            setOnClickListener { openDetail(s1.text.toString(), b1.text.toString(), icon1) }
        }
        findViewById<MaterialButton>(R.id.tocButton2).apply {
            text = "2. " + s2.text
            setOnClickListener { openDetail(s2.text.toString(), b2.text.toString(), icon2) }
        }
        findViewById<MaterialButton>(R.id.tocButton3).apply {
            text = "3. " + s3.text
            setOnClickListener { openDetail(s3.text.toString(), b3.text.toString(), icon3) }
        }
    }

    private fun openDetail(title: String, body: String, iconRes: Int) {
        startActivity(Intent(this, SectionDetailActivity::class.java).apply {
            putExtra("title", title)
            putExtra("body", body)
            putExtra("icon", iconRes)
        })
    }

    private fun getSectionIcon(section: Int): Int {
        return when ("trivia") {
            "rules" -> if (section == 1) R.drawable.ic_book_24 else if (section == 2) R.drawable.ic_info_24 else R.drawable.ic_lightbulb_24
            "terms" -> if (section == 1) R.drawable.ic_info_24 else if (section == 2) R.drawable.ic_book_24 else R.drawable.ic_history_24
            "history" -> R.drawable.ic_history_24
            "trivia" -> if (section == 1) R.drawable.ic_info_24 else R.drawable.ic_lightbulb_24
            "about" -> if (section == 1) R.drawable.ic_info_24 else if (section == 2) R.drawable.ic_book_24 else R.drawable.ic_lightbulb_24
            "settings" -> if (section == 1) R.drawable.ic_info_24 else if (section == 2) R.drawable.ic_history_24 else R.drawable.ic_lightbulb_24
            else -> R.drawable.ic_info_24
        }
    }
}
