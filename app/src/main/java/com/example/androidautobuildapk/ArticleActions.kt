package com.example.androidautobuildapk

import android.widget.Toast
import com.google.android.material.button.MaterialButton

object ArticleActions {
    fun bind(activity: BaseActivity, articleName: String) {
        val bookmark = activity.findViewById<MaterialButton>(R.id.bookmarkButton)
        val favorite = activity.findViewById<MaterialButton>(R.id.favoriteButton)

        fun refresh() {
            bookmark.text = if (UserPreferences.isBookmarked(activity, articleName)) "📑 Bookmarked" else "📑 Bookmark"
            favorite.text = if (UserPreferences.isFavorited(activity, articleName)) "⭐ Favorited" else "⭐ Favorite"
        }

        bookmark.setOnClickListener {
            val added = UserPreferences.toggleBookmark(activity, articleName)
            Toast.makeText(activity, if (added) R.string.added_bookmark else R.string.removed_bookmark, Toast.LENGTH_SHORT).show()
            refresh()
        }
        favorite.setOnClickListener {
            val added = UserPreferences.toggleFavorite(activity, articleName)
            Toast.makeText(activity, if (added) R.string.added_favorite else R.string.removed_favorite, Toast.LENGTH_SHORT).show()
            refresh()
        }
        refresh()
    }
}
