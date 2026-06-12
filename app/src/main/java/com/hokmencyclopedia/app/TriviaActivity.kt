package com.hokmencyclopedia.app

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class TriviaActivity : BaseActivity() {

    private val questions = ArticleRepository.questions()
    private var index = 0
    private var score = 0
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)
        title = getString(R.string.menu_trivia)
        index = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        score = savedInstanceState?.getInt(KEY_SCORE) ?: 0
        answered = savedInstanceState?.getBoolean(KEY_ANSWERED) ?: false
        if (index >= questions.size) showResult() else showQuestion()
        ArticleActions.bind(this, getString(R.string.menu_trivia))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_INDEX, index)
        outState.putInt(KEY_SCORE, score)
        outState.putBoolean(KEY_ANSWERED, answered)
        super.onSaveInstanceState(outState)
    }

    private fun showQuestion() {
        answered = false
        val q = questions[index]
        findViewById<TextView>(R.id.quizProgress).text = getString(
            R.string.quiz_progress_format,
            index + 1,
            questions.size,
            q.difficulty.name.toLowerCase().capitalize(),
        )
        findViewById<TextView>(R.id.quizQuestion).text = q.prompt
        val buttonIds = listOf(R.id.choiceA, R.id.choiceB, R.id.choiceC, R.id.choiceD)
        buttonIds.forEachIndexed { i, id ->
            findViewById<MaterialButton>(id).apply {
                text = q.options[i]
                isEnabled = true
                setOnClickListener { answer(i) }
                visibility = View.VISIBLE
            }
        }
        findViewById<TextView>(R.id.quizResult).visibility = View.GONE
        findViewById<MaterialButton>(R.id.retryQuiz).visibility = View.GONE
    }

    private fun answer(choice: Int) {
        if (answered) return
        answered = true
        val q = questions[index]
        val correct = choice == q.correctIndex
        if (correct) score++
        listOf(R.id.choiceA, R.id.choiceB, R.id.choiceC, R.id.choiceD).forEach {
            findViewById<MaterialButton>(it).isEnabled = false
        }
        findViewById<TextView>(R.id.quizResult).apply {
            visibility = View.VISIBLE
            text = getString(
                if (correct) R.string.quiz_correct_format else R.string.quiz_incorrect_format,
                q.explanation,
            )
        }
        findViewById<MaterialButton>(R.id.retryQuiz).apply {
            visibility = View.VISIBLE
            text = getString(if (index + 1 < questions.size) R.string.next_question else R.string.show_score)
            setOnClickListener {
                index++
                if (index < questions.size) showQuestion() else showResult()
            }
        }
    }

    private fun showResult() {
        findViewById<TextView>(R.id.quizProgress).text = getString(R.string.quiz_complete)
        findViewById<TextView>(R.id.quizQuestion).text = getString(R.string.quiz_great_job)
        listOf(R.id.choiceA, R.id.choiceB, R.id.choiceC, R.id.choiceD).forEach {
            findViewById<MaterialButton>(it).visibility = View.GONE
        }
        findViewById<TextView>(R.id.quizResult).apply {
            visibility = View.VISIBLE
            text = getString(R.string.quiz_score_format, score, questions.size)
        }
        findViewById<MaterialButton>(R.id.retryQuiz).apply {
            visibility = View.VISIBLE
            text = getString(R.string.retry_quiz)
            setOnClickListener {
                index = 0
                score = 0
                showQuestion()
            }
        }
    }

    companion object {
        private const val KEY_INDEX = "index"
        private const val KEY_SCORE = "score"
        private const val KEY_ANSWERED = "answered"
    }
}
