package com.hokmencyclopedia.app

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
        bindArticleCard(R.id.learningPathCard, "learning")
        bindArticleCard(R.id.cardHierarchyCard, "hierarchy")
        bindArticleCard(R.id.strategyGuidesCard, "strategy")
        bindArticleCard(R.id.partnerTacticsCard, "partner")
        bindArticleCard(R.id.advancedTechniquesCard, "advanced")
        bindArticleCard(R.id.gameSetupCard, "setup")
        bindArticleCard(R.id.dealingRulesCard, "deal")
        bindArticleCard(R.id.trumpStrategyCard, "trump_strategy")
        bindArticleCard(R.id.scoringSystemsCard, "scoring")
        bindArticleCard(R.id.houseRulesCard, "house_rules")
        bindArticleCard(R.id.beginnerMistakesCard, "mistakes")
        bindArticleCard(R.id.handWalkthroughCard, "walkthrough")
        bindArticleCard(R.id.privacyCard, "privacy")
        bindArticleCard(R.id.sourcesCard, "sources")

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

    private fun bindArticleCard(cardId: Int, key: String) {
        val article = ArticleRepository.articles.first { it.key == key }
        findViewById<View>(cardId).setOnClickListener { openArticle(article) }
    }

    private fun openArticle(article: ArticleRepository.Article) {
        startActivity(Intent(this, SectionDetailActivity::class.java).apply {
            putExtra(SectionDetailActivity.EXTRA_ARTICLE_KEY, article.key)
        })
    }
}
