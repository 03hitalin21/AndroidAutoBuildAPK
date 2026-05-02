package com.example.androidautobuildapk

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class TriviaActivity : BaseActivity() {

    private data class Question(val prompt: String, val options: List<String>, val correctIndex: Int)

    private val questions = listOf(
        Question("How many players are in standard Hokm?", listOf("4", "2", "5", "6"), 0),
        Question("What does \"Hokm\" mean in gameplay?", listOf("Trump suit", "Dealer", "Score", "Shuffle"), 0),
        Question("If you cannot follow suit, what can you do?", listOf("Play any card, including trump", "Skip your turn", "Draw a card", "Ask partner"), 0),
        Question("How many points are commonly needed to win a standard game?", listOf("7", "3", "10", "13"), 0),
        Question("Do partners sit next to or opposite each other?", listOf("Opposite", "Next to each other", "Randomly", "They rotate each trick"), 0),
        Question("What is it called when you fail to follow suit while able to?", listOf("Reneging", "Overtrump", "Slough", "Void"), 0),
        Question("Who leads the first trick?", listOf("Depends on variant: often player after dealer or dealer", "Always partner of dealer", "Always youngest player", "No one, cards are compared"), 0),
        Question("Can the trump suit change mid-game in the same hand?", listOf("No", "Yes after 3 tricks", "Only if tied", "Only in finals"), 0),
        Question("What is \"void\"?", listOf("Having no cards in a suit", "Having all trumps", "Winning every trick", "A cancelled game"), 0),
        Question("What happens if your team wins no tricks?", listOf("Some variants penalize with Beshkan", "You instantly win", "Cards are reshuffled with no score", "Opponent loses a point"), 0)
    )

    private var index = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)
        title = getString(R.string.menu_trivia)
        showQuestion()
        ArticleActions.bind(this, getString(R.string.menu_trivia))
    }

    private fun showQuestion() {
        val q = questions[index]
        findViewById<TextView>(R.id.quizProgress).text = "Question ${index + 1} of ${questions.size}"
        findViewById<TextView>(R.id.quizQuestion).text = q.prompt
        val buttonIds = listOf(R.id.choiceA, R.id.choiceB, R.id.choiceC, R.id.choiceD)
        buttonIds.forEachIndexed { i, id ->
            findViewById<MaterialButton>(id).apply {
                text = q.options[i]
                setOnClickListener { answer(i) }
                visibility = View.VISIBLE
            }
        }
        findViewById<TextView>(R.id.quizResult).visibility = View.GONE
        findViewById<MaterialButton>(R.id.retryQuiz).visibility = View.GONE
    }

    private fun answer(choice: Int) {
        if (choice == questions[index].correctIndex) score++
        index++
        if (index < questions.size) {
            showQuestion()
        } else {
            showResult()
        }
    }

    private fun showResult() {
        findViewById<TextView>(R.id.quizProgress).text = "Quiz complete"
        findViewById<TextView>(R.id.quizQuestion).text = "Great job!"
        listOf(R.id.choiceA, R.id.choiceB, R.id.choiceC, R.id.choiceD).forEach {
            findViewById<MaterialButton>(it).visibility = View.GONE
        }
        findViewById<TextView>(R.id.quizResult).apply {
            visibility = View.VISIBLE
            text = "Your score: $score / ${questions.size}"
        }
        findViewById<MaterialButton>(R.id.retryQuiz).apply {
            visibility = View.VISIBLE
            setOnClickListener {
                index = 0
                score = 0
                showQuestion()
            }
        }
    }
}
