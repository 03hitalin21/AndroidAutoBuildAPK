package com.hokmencyclopedia.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.hokmencyclopedia.app.core.preferences.UserPreferences

class BookmarksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_list)
        title = getString(R.string.menu_bookmarks)
        findViewById<TextView>(R.id.savedTitle).text = getString(R.string.menu_bookmarks)
        bind(UserPreferences.getBookmarks(this).sorted())
    }

    private fun bind(items: List<String>) {
        findViewById<TextView>(R.id.emptyState).visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
        findViewById<ListView>(R.id.savedList).apply {
            adapter = ArrayAdapter(this@BookmarksActivity, android.R.layout.simple_list_item_1, items)
            setOnItemClickListener { _, _, position, _ -> openSavedItem(items[position]) }
        }
    }

    private fun openSavedItem(title: String) {
        val article = ArticleRepository.findByTitle(this, title)
        startActivity(Intent(this, SectionDetailActivity::class.java).apply {
            if (article != null) {
                putExtra(SectionDetailActivity.EXTRA_ARTICLE_KEY, article.key)
            } else {
                putExtra(SectionDetailActivity.EXTRA_TITLE, title)
                putExtra(SectionDetailActivity.EXTRA_BODY, getString(R.string.saved_item_missing_body))
                putExtra(SectionDetailActivity.EXTRA_ICON, R.drawable.ic_info_24)
            }
        })
    }
}
