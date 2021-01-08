package com.po4yka.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

const val EXTRA_ANSWER_SHOWN =
    "com.po4yka.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "com.po4yka.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    private val cheatViewModel: CheatActivityViewModel by lazy {
        ViewModelProvider(this).get(CheatActivityViewModel::class.java)
    }

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        cheatViewModel.rightAnswer = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        if (cheatViewModel.isAnswerShown) showRightAnswer()

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
    }

    fun showRightAnswer() {
        answerTextView.setText(cheatViewModel.getAnswer())
        setAnswerShowResult()
    }

    private fun setAnswerShowResult() {
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, true)
        setResult(Activity.RESULT_OK, data)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}