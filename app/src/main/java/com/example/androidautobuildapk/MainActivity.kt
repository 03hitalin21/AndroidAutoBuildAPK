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
        bindDetailCard(R.id.learningPathCard, R.string.menu_learning_path, R.string.learning_path_body, R.drawable.ic_book_24)
        bindDetailCard(R.id.cardHierarchyCard, R.string.menu_card_hierarchy, R.string.card_hierarchy_body, R.drawable.ic_info_24)
        bindDetailCard(R.id.strategyGuidesCard, R.string.menu_strategy_guides, R.string.strategy_guides_body, R.drawable.ic_lightbulb_24)
        bindDetailCard(R.id.partnerTacticsCard, R.string.menu_partner_tactics, R.string.partner_tactics_body, R.drawable.ic_info_24)
        bindDetailCard(R.id.advancedTechniquesCard, R.string.menu_advanced_techniques, R.string.advanced_techniques_body, R.drawable.ic_lightbulb_24)
        bindDetailCard(R.id.sourcesCard, R.string.menu_sources, R.string.content_sources_note, R.drawable.ic_info_24)

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

    private fun bindDetailCard(cardId: Int, titleRes: Int, bodyRes: Int, iconRes: Int) {
        findViewById<View>(cardId).setOnClickListener {
            startActivity(Intent(this, SectionDetailActivity::class.java).apply {
                putExtra("title", getString(titleRes))
                putExtra("body", getString(bodyRes))
                putExtra("icon", iconRes)
            })
        }
    }
}
