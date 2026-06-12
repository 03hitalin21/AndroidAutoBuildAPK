package com.hokmencyclopedia.app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SectionDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_detail)

        val article = intent.getStringExtra(EXTRA_ARTICLE_KEY)?.let { key ->
            ArticleRepository.articles.firstOrNull { it.key == key }
        }
        val titleText = article?.let { getString(it.titleRes) } ?: intent.getStringExtra(EXTRA_TITLE) ?: getString(R.string.section_default_title)
        val bodyText = article?.let { getString(it.bodyRes) } ?: intent.getStringExtra(EXTRA_BODY).orEmpty()
        val iconRes = article?.iconRes ?: intent.getIntExtra(EXTRA_ICON, R.drawable.ic_info_24)

        title = titleText
        findViewById<ImageView>(R.id.detailIcon).setImageResource(iconRes)
        findViewById<TextView>(R.id.detailTitle).text = titleText
        findViewById<TextView>(R.id.detailBody).text = getString(R.string.detail_body_with_sources, bodyText, getString(R.string.content_sources_note))
        ArticleActions.bind(this, titleText)
    }

    companion object {
        const val EXTRA_ARTICLE_KEY = "article_key"
        const val EXTRA_TITLE = "title"
        const val EXTRA_BODY = "body"
        const val EXTRA_ICON = "icon"
    }
}
