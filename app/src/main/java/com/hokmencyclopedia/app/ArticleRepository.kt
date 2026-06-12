package com.hokmencyclopedia.app

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

object ArticleRepository {
    data class Article(
        val key: String,
        @StringRes val titleRes: Int,
        @StringRes val bodyRes: Int,
        @DrawableRes val iconRes: Int = R.drawable.ic_info_24,
    )

    data class QuizQuestion(
        val prompt: String,
        val options: List<String>,
        val correctIndex: Int,
        val explanation: String,
        val difficulty: Difficulty,
    )

    enum class Difficulty { EASY, MEDIUM, HARD }

    val articles = listOf(
        Article("rules", R.string.menu_rules, R.string.rules_content_expanded, R.drawable.ic_book_24),
        Article("terms", R.string.menu_terms, R.string.terms_content, R.drawable.ic_info_24),
        Article("history", R.string.menu_history, R.string.history_content, R.drawable.ic_history_24),
        Article("trivia", R.string.menu_trivia, R.string.trivia_content, R.drawable.ic_lightbulb_24),
        Article("learning", R.string.menu_learning_path, R.string.learning_path_body, R.drawable.ic_book_24),
        Article("hierarchy", R.string.menu_card_hierarchy, R.string.card_hierarchy_body, R.drawable.ic_info_24),
        Article("strategy", R.string.menu_strategy_guides, R.string.strategy_guides_body, R.drawable.ic_lightbulb_24),
        Article("partner", R.string.menu_partner_tactics, R.string.partner_tactics_body, R.drawable.ic_info_24),
        Article("advanced", R.string.menu_advanced_techniques, R.string.advanced_techniques_body, R.drawable.ic_lightbulb_24),
        Article("setup", R.string.menu_game_setup, R.string.game_setup_body, R.drawable.ic_book_24),
        Article("deal", R.string.menu_dealing_rules, R.string.dealing_rules_body, R.drawable.ic_info_24),
        Article("trump_strategy", R.string.menu_trump_strategy, R.string.trump_strategy_body, R.drawable.ic_lightbulb_24),
        Article("scoring", R.string.menu_scoring_systems, R.string.scoring_systems_body, R.drawable.ic_info_24),
        Article("house_rules", R.string.menu_house_rules, R.string.house_rules_body, R.drawable.ic_info_24),
        Article("mistakes", R.string.menu_beginner_mistakes, R.string.beginner_mistakes_body, R.drawable.ic_lightbulb_24),
        Article("walkthrough", R.string.menu_hand_walkthrough, R.string.hand_walkthrough_body, R.drawable.ic_book_24),
        Article("privacy", R.string.menu_privacy_policy, R.string.privacy_policy_body, R.drawable.ic_info_24),
        Article("sources", R.string.menu_sources, R.string.sources_copyright_body, R.drawable.ic_info_24),
    )

    fun findByTitle(context: Context, title: String): Article? = articles.firstOrNull { context.getString(it.titleRes) == title }

    fun questions(): List<QuizQuestion> = baseQuestions + generatedQuestions

    private val baseQuestions = listOf(
        QuizQuestion("How many players are in standard partnership Hokm?", listOf("4", "2", "5", "6"), 0, "Standard partnership Hokm uses four players in two teams.", Difficulty.EASY),
        QuizQuestion("What does Hokm mean during a hand?", listOf("Trump suit", "Dealer", "Score", "Discard pile"), 0, "Hokm is the ruling or trump suit chosen for the hand.", Difficulty.EASY),
        QuizQuestion("Where do partners sit?", listOf("Opposite each other", "Side by side", "Anywhere after each trick", "Only beside the Hakem"), 0, "Partners sit opposite so turn order alternates between teams.", Difficulty.EASY),
        QuizQuestion("How many cards does each player receive?", listOf("13", "10", "7", "5"), 0, "A 52-card deck divided among four players gives each player 13 cards.", Difficulty.EASY),
        QuizQuestion("Who chooses trump in many common rules?", listOf("Hakem", "Last trick winner", "Dealer's partner", "Lowest card holder"), 0, "The Hakem chooses the trump suit after seeing the opening packet in many tables.", Difficulty.EASY),
        QuizQuestion("What must you do if you can match the led suit?", listOf("Follow suit", "Always trump", "Pass", "Ask partner"), 0, "Following suit when possible is mandatory.", Difficulty.EASY),
        QuizQuestion("What is a trick?", listOf("One card from each player", "A full game", "A scoring penalty", "A shuffle method"), 0, "A trick is the four-card round played one card per player.", Difficulty.EASY),
        QuizQuestion("What usually wins a trick with no trump played?", listOf("Highest card of the led suit", "Lowest card", "Highest off-suit card", "Dealer's card"), 0, "Without trump, only cards in the led suit compete to win.", Difficulty.EASY),
        QuizQuestion("What is being void in a suit?", listOf("Having no cards in that suit", "Having all aces", "Holding only trump", "Misdealing"), 0, "A void means you cannot follow that suit because you hold none.", Difficulty.EASY),
        QuizQuestion("What score commonly wins a hand?", listOf("7 tricks", "3 tricks", "All 13 tricks only", "The last trick only"), 0, "A team that reaches 7 of 13 tricks cannot be caught.", Difficulty.EASY),
        QuizQuestion("Why is the opening five-card packet important?", listOf("It guides trump choice", "It ends the hand", "It sets the final score", "It removes a player"), 0, "The Hakem often chooses trump from information in the first packet.", Difficulty.MEDIUM),
        QuizQuestion("Which rank order is usually highest to lowest?", listOf("A K Q J 10 ... 2", "2 3 4 ... A", "J Q K A 10", "Suits have no ranks"), 0, "Hokm normally uses ace high down to two low.", Difficulty.EASY),
        QuizQuestion("When can an off-suit trump beat the led suit?", listOf("When the player cannot follow suit", "Any time", "Only first trick", "Never"), 0, "Trump is legally played when the player is void in the led suit.", Difficulty.MEDIUM),
        QuizQuestion("What should players agree on before play?", listOf("House rules and scoring", "Secret signals", "Phone brand", "Winner's age"), 0, "Local rules vary, so scoring and penalties should be agreed first.", Difficulty.EASY),
        QuizQuestion("What is reneging?", listOf("Failing to follow suit while able", "Winning seven tricks", "Choosing trump", "Counting cards"), 0, "Reneging breaks the follow-suit rule.", Difficulty.MEDIUM),
        QuizQuestion("Why count suits?", listOf("To estimate remaining cards and voids", "To change card ranks", "To avoid dealing", "To pick seats"), 0, "Counting played suits helps predict who can follow or trump.", Difficulty.MEDIUM),
        QuizQuestion("What is tempo in Hokm strategy?", listOf("Control over who leads and when", "Music speed", "Shuffling style", "Card color"), 0, "Tempo is practical control of the lead and timing.", Difficulty.HARD),
        QuizQuestion("Why save a high trump?", listOf("To stop an opponent run later", "Because it cannot win", "Because it must be discarded first", "To avoid scoring"), 0, "A high trump can be endgame insurance.", Difficulty.MEDIUM),
        QuizQuestion("What does leading a suit twice often test?", listOf("Whether opponents are short or void", "Whether the deck is marked", "Whether partner can speak", "Whether scoring is complete"), 0, "Repeated leads reveal who can still follow that suit.", Difficulty.MEDIUM),
        QuizQuestion("What is a safe beginner habit?", listOf("Play low when partner is already winning", "Trump partner's winning ace", "Ignore the led suit", "Reveal your hand"), 0, "Avoid wasting strength when partner already controls the trick.", Difficulty.EASY),
    )

    private val generatedQuestions = listOf(
        "Which seating pattern supports partnership play?" to "Opposite partners",
        "What is the main goal in each hand?" to "Reach seven tricks first",
        "Which card is usually strongest inside one suit?" to "Ace",
        "Which card is usually weakest inside one suit?" to "Two",
        "What should you protect if it is a guaranteed late winner?" to "Control card",
        "What can a low discard do?" to "Preserve stronger cards",
        "Why avoid unnecessary trumping?" to "It may waste future control",
        "What does a player show by discarding off-suit?" to "They are void in the led suit",
        "Who leads the next trick?" to "The previous trick winner",
        "Why is partner observation legal and useful?" to "Card choices reveal patterns",
        "What is a common match target?" to "Seven hand points",
        "What should happen after a disputed local rule appears?" to "Use the pre-agreed house rule",
        "What is Beshkan often related to?" to "Special high-value results or penalties",
        "Which phase emphasizes discovering voids?" to "Early phase",
        "Which phase emphasizes exact winner counting?" to "End phase",
        "What does drawing trump mean?" to "Leading trump to remove opponents' trumps",
        "When is drawing trump risky?" to "When partner needs trump for defense",
        "What does sloughing usually mean?" to "Discarding a non-winning off-suit card",
        "What makes Hokm educational for beginners?" to "Clear rules plus repeated examples",
        "Why are explanations added to quiz answers?" to "They teach the rule behind the answer",
        "What does a house rule page prevent?" to "Confusion from local variations",
        "Why include a full hand walkthrough?" to "It connects rules to real decisions",
        "Which action saves content locally?" to "Bookmark or favorite",
        "Does this app need internet for lessons?" to "No, lessons are offline",
        "What data does the privacy page emphasize?" to "Local-only preferences",
        "Which source practice helps Bazaar review?" to "Specific sources and access dates",
        "Why should beginners not chase every trick?" to "Team control matters more",
        "What does trump selection balance?" to "Length, high cards, and partner risk",
        "What is a strong reason to lead an ace?" to "To capture a reliable early trick",
        "What is a reason not to lead an ace?" to "It may reveal control too early",
        "What should you do if partner is winning a trick?" to "Avoid wasting a stronger card",
        "Why track the hand score during play?" to "It changes risk decisions",
        "What does a clear source page show?" to "Attribution and originality",
        "Why store bookmarks locally?" to "Offline access without account data"
    ).mapIndexed { i, pair ->
        QuizQuestion(
            prompt = pair.first,
            options = listOf(pair.second, "Shuffle again", "Skip the trick", "Change partners"),
            correctIndex = 0,
            explanation = "Correct: ${pair.second}. This follows the lesson structure in the rules, strategy, or compliance sections.",
            difficulty = when {
                i < 10 -> Difficulty.EASY
                i < 22 -> Difficulty.MEDIUM
                else -> Difficulty.HARD
            }
        )
    }
}
