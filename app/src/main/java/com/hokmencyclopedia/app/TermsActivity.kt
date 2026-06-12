package com.hokmencyclopedia.app

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialog

class TermsActivity : BaseActivity() {

    private data class HokmTerm(val name: String, val definition: String, val example: String)

    private val terms = listOf(
        HokmTerm("Trump (Hokm)", "The trump suit is the suit declared as Hokm for a hand. Any trump card beats cards from non-trump suits in the same trick.", "Example: Hearts are trump. If clubs are led, a heart can still win the trick if no higher heart is played later."),
        HokmTerm("Trick", "A trick is one round where each of the four players plays one card. The highest card in the lead suit wins, unless trump is played.", "Example: Four players each place one card; the winning card takes the trick for that team."),
        HokmTerm("Lead", "The lead is the first card played in a trick, and it sets the suit that others must follow if they can.", "Example: If the lead is 10 of spades, players who hold spades should play a spade."),
        HokmTerm("Void", "A player is void in a suit when they have no cards of that suit left in hand.", "Example: If diamonds are led and you have no diamonds, you are void in diamonds."),
        HokmTerm("Reneging", "Reneging (also called revoke) is failing to follow suit even though you had a card of that suit. It is usually a penalty.", "Example: Spades are led, you secretly keep your spade and play a heart instead. That is reneging."),
        HokmTerm("Follow suit", "To follow suit means playing a card in the same suit as the lead card when you are able to.", "Example: Clubs are led, and you hold clubs, so you must play a club."),
        HokmTerm("Overtrump", "Overtrump means playing a higher trump card than the trump currently winning the trick.", "Example: An opponent plays trump 9. You play trump queen to overtrump and take control."),
        HokmTerm("Undertrump", "Undertrump means playing a lower trump card than one already played in the same trick, usually when you cannot overtrump.", "Example: Trump king is already on table, and you only have trump 5, so you undertrump."),
        HokmTerm("Slough / Throw off", "Sloughing means discarding a non-trump, off-suit card when you cannot follow the lead suit and choose not to trump.", "Example: Hearts are led, you are void in hearts, and you discard a low club to save trumps."),
        HokmTerm("Beshkan", "Beshkan is a term used in some circles when a team fails to win any trick in a hand; some variants apply an extra penalty or bonus swing.", "Example: Team A takes all tricks; Team B gets a beshkan and may lose extra points depending on house rules."),
        HokmTerm("Hakem", "The Hakem chooses the trump suit for the hand. The role is powerful because the chosen suit shapes every later trick.", "Example: After seeing the first five cards, the Hakem declares spades as Hokm."),
        HokmTerm("Hand", "A hand is one complete deal from the first card dealt until one team has won enough tricks or all 13 tricks are played.", "Example: If your team reaches 7 tricks, that hand is normally won."),
        HokmTerm("Packet deal", "A packet deal gives several cards at a time instead of one card at a time. Hokm tables often use packet patterns by local custom.", "Example: A dealer may deal 5 cards first, then continue with packets after trump is declared."),
        HokmTerm("Cut", "Cutting the deck means another player splits the shuffled deck before dealing, a common fairness habit in card games.", "Example: The dealer shuffles and the player on the left cuts before cards are dealt."),
        HokmTerm("Table rule", "A table rule is a local agreement that should be clarified before play, especially for scoring bonuses and penalties.", "Example: Before the first hand, players agree whether Beshkan counts as an extra point."),
        HokmTerm("Card counting", "Card counting in Hokm means remembering which suits and high cards have already appeared, not using any hidden device.", "Example: If the ace and king of clubs have already been played, the queen of clubs may become stronger."),
        HokmTerm("Tempo", "Tempo is the practical control gained by winning a trick and choosing the next lead.", "Example: Winning with a high trump gives you tempo because you decide which suit begins next."),
        HokmTerm("Partner protection", "Partner protection means avoiding plays that waste your partner's likely winner or expose their weak suit unnecessarily.", "Example: If your partner is winning a trick, you may save your trump instead of overtrumping them.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)
        title = getString(R.string.menu_terms)

        val searchBar = findViewById<EditText>(R.id.searchTerms)
        val listView = findViewById<ListView>(R.id.termsList)
        val names = terms.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names.toMutableList())
        listView.adapter = adapter

        searchBar.addTextChangedListener(SimpleTextWatcher { text ->
            val filtered = terms.filter { it.name.contains(text, ignoreCase = true) }.map { it.name }
            adapter.clear()
            adapter.addAll(filtered)
            adapter.notifyDataSetChanged()
        })

        listView.setOnItemClickListener { _, _, position, _ ->
            val termName = adapter.getItem(position) ?: return@setOnItemClickListener
            val term = terms.first { it.name == termName }
            showTermDialog(term)
        }

        ArticleActions.bind(this, getString(R.string.menu_terms))
    }

    private fun showTermDialog(term: HokmTerm) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_term, null)
        view.findViewById<TextView>(R.id.termTitle).text = term.name
        view.findViewById<TextView>(R.id.termDefinition).text = term.definition
        view.findViewById<TextView>(R.id.termExample).text = term.example
        dialog.setContentView(view)
        dialog.show()
    }
}
