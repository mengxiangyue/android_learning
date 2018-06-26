package io.mshare.geoquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mQuestionBand= listOf(
                Question(R.string.question_australia, true),
                Question(R.string.question_oceans, true),
                Question(R.string.question_mideast, false),
                Question(R.string.question_africa, false),
                Question(R.string.question_americas, true),
                Question(R.string.question_asia, true)
            )

    private var mCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateQuestion()

        btn_true.setOnClickListener {
//            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
            checkAnswer(true)
        }

        btn_false.setOnClickListener {
//            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
            checkAnswer(false)
        }

        btn_next.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBand.size
            updateQuestion()
        }
    }

    private fun updateQuestion() {
        tv_question.setText(mQuestionBand[mCurrentIndex].textResId)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBand[mCurrentIndex].answerTrue
        var messgeeResId = 0
        if (userPressedTrue == answerIsTrue) {
            messgeeResId = R.string.correct_toast
        } else {
            messgeeResId = R.string.incorrect_toast
        }
        Toast.makeText(this, messgeeResId, Toast.LENGTH_SHORT).show()
    }
}
