package com.example.androidautobuildapk

import android.os.Bundle

class HistoryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        title = getString(R.string.menu_history)
        ArticleActions.bind(this, getString(R.string.menu_history))
    }
}
