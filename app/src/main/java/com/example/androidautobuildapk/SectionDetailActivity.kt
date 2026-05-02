package com.example.androidautobuildapk

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SectionDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_detail)

        val titleText = intent.getStringExtra("title") ?: "Section"
        val bodyText = intent.getStringExtra("body") ?: ""
        val iconRes = intent.getIntExtra("icon", R.drawable.ic_info_24)

        title = titleText
        findViewById<ImageView>(R.id.detailIcon).setImageResource(iconRes)
        findViewById<TextView>(R.id.detailTitle).text = titleText
        findViewById<TextView>(R.id.detailBody).text = bodyText
    }
}
