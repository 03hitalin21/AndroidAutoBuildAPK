package com.example.androidautobuildapk

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class TermsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_page)
        title = getString(R.string.menu_terms)

        val s1 = findViewById<TextView>(R.id.section1Title)
        val s2 = findViewById<TextView>(R.id.section2Title)
        val s3 = findViewById<TextView>(R.id.section3Title)
        val b1 = findViewById<TextView>(R.id.section1Body)
        val b2 = findViewById<TextView>(R.id.section2Body)
        val b3 = findViewById<TextView>(R.id.section3Body)
        s1.text = getString(R.string.terms_section1_title); b1.text = getString(R.string.terms_section1_body)
        s2.text = getString(R.string.terms_section2_title); b2.text = getString(R.string.terms_section2_body)
        s3.text = getString(R.string.terms_section3_title); b3.text = getString(R.string.terms_section3_body)
        findViewById<TextView>(R.id.highlightNote).text = getString(R.string.terms_note)

        findViewById<MaterialButton>(R.id.tocButton1).apply { text = "1. " + s1.text; setOnClickListener { openDetail(s1.text.toString(), b1.text.toString()) } }
        findViewById<MaterialButton>(R.id.tocButton2).apply { text = "2. " + s2.text; setOnClickListener { openDetail(s2.text.toString(), b2.text.toString()) } }
        findViewById<MaterialButton>(R.id.tocButton3).apply { text = "3. " + s3.text; setOnClickListener { openDetail(s3.text.toString(), b3.text.toString()) } }

        ArticleActions.bind(this, getString(R.string.menu_terms))
    }

    private fun openDetail(title: String, body: String) {
        startActivity(Intent(this, SectionDetailActivity::class.java).apply {
            putExtra("title", title)
            putExtra("body", body)
            putExtra("icon", R.drawable.ic_info_24)
        })
    }
}
