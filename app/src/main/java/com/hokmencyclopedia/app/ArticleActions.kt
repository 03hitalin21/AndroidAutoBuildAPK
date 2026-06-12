package com.hokmencyclopedia.app

import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.hokmencyclopedia.app.core.preferences.UserPreferences

object ArticleActions {
    fun bind(activity: BaseActivity, articleName: String) {
        val bookmark = activity.findViewById<MaterialButton?>(R.id.bookmarkButton)
        val favorite = activity.findViewById<MaterialButton?>(R.id.favoriteButton)

        if (bookmark == null && favorite == null) return

        fun refresh() {
            bookmark?.text = if (UserPreferences.isBookmarked(activity, articleName)) {
                activity.getString(R.string.bookmarked_label)
            } else {
                activity.getString(R.string.bookmark_label)
            }
            favorite?.text = if (UserPreferences.isFavorited(activity, articleName)) {
                activity.getString(R.string.favorited_label)
            } else {
                activity.getString(R.string.favorite_label)
            }
        }

        bookmark?.setOnClickListener {
            val added = UserPreferences.toggleBookmark(activity, articleName)
            Toast.makeText(activity, if (added) R.string.added_bookmark else R.string.removed_bookmark, Toast.LENGTH_SHORT).show()
            refresh()
        }
        favorite?.setOnClickListener {
            val added = UserPreferences.toggleFavorite(activity, articleName)
            Toast.makeText(activity, if (added) R.string.added_favorite else R.string.removed_favorite, Toast.LENGTH_SHORT).show()
            refresh()
        }
        refresh()
    }
}
