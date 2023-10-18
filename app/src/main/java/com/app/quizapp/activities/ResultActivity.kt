package com.app.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.app.quizapp.MainActivity
import com.app.quizapp.R
import com.app.quizapp.utils.Constants

class ResultActivity : AppCompatActivity() {

    private lateinit var finishButton: Button
    private lateinit var nameResultActivity:TextView
    private lateinit var scoreResultActivity:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        finishButton=findViewById(R.id.finishButton)
        nameResultActivity=findViewById(R.id.user_name)
        scoreResultActivity=findViewById(R.id.score)

        val totalQuestions=intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val name=intent.getStringExtra(Constants.USER_NAME)
        val score=intent.getIntExtra(Constants.SCORE,0)

        scoreResultActivity.text="Your Score is $score out of $totalQuestions questions"
        nameResultActivity.text =name

        finishButton.setOnClickListener{
            Intent(this@ResultActivity,MainActivity::class.java).also{
                startActivity(it)
            }
        }

    }
}