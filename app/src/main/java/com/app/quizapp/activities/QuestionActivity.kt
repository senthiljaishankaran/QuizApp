package com.app.quizapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.app.quizapp.R
import com.app.quizapp.model.Question
import com.app.quizapp.utils.Constants

class QuestionActivity : AppCompatActivity() ,View.OnClickListener{

    private lateinit var questionList: MutableList<Question>
    private lateinit var question: TextView
    private lateinit var imageFlag:ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var textProgressBar:TextView
    private lateinit var optionOne:TextView
    private lateinit var optionTwo:TextView
    private lateinit var optionThree:TextView
    private lateinit var optionFour:TextView
    private lateinit var checkButton:Button
    private var questionCounter=0
    private var selectedAnswer=0
    private var answered=false
    private lateinit var currentQuestion:Question
    private var score=0
//    private lateinit var name:String
    private var name:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        question=findViewById(R.id.questionTextView)
        imageFlag=findViewById(R.id.image_flag)
        progressBar=findViewById(R.id.questionProgressBar)
        textProgressBar=findViewById(R.id.viewProgressText)
        optionOne=findViewById(R.id.optionOneAnswer)
        optionTwo=findViewById(R.id.optionTwoAnswer)
        optionThree=findViewById(R.id.optionThreeAnswer)
        optionFour=findViewById(R.id.optionFourAnswer)
        checkButton=findViewById(R.id.checkBtn)

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        checkButton.setOnClickListener(this)

        questionList=Constants.getQuestions()

        showNextQuestion()

        if(intent.hasExtra(Constants.USER_NAME)){
            name=intent.getStringExtra(Constants.USER_NAME)!!
        }

    }

    private fun showNextQuestion(){

        if(questionCounter < questionList.size){
            currentQuestion=questionList[questionCounter]
            checkButton.text="Check"

            resetOption()
            val questions=questionList[questionCounter]
            imageFlag.setImageResource(questions.image)
            progressBar.progress=questionCounter
            textProgressBar.text="${questionCounter+1}/${progressBar.max}"
            question.text=questions.question
            optionOne.text=questions.optionOne
            optionTwo.text=questions.optionTwo
            optionThree.text=questions.optionThree
            optionFour.text=questions.optionFour
        }else{
            checkButton.text="Finish"
            Intent(this@QuestionActivity,ResultActivity::class.java).also{

                intent.putExtra(Constants.USER_NAME,name)
                intent.putExtra(Constants.SCORE,score)
                intent.putExtra(Constants.TOTAL_QUESTIONS,questionList.size)

                startActivity(it)
            }
        }
        questionCounter++
        answered=false
    }

    private fun resetOption(){
        val options= mutableListOf<TextView>()
        options.add(optionOne)
        options.add(optionTwo)
        options.add(optionThree)
        options.add(optionFour)

        for(option in options ){
            option.setTextColor(Color.parseColor("#FF000000"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.default_border_for_options)
        }

    }

    private fun selectedOption(textView: TextView,selectedOptionNumber:Int){
        resetOption()
        selectedAnswer=selectedOptionNumber
        textView.setTextColor(Color.parseColor("#FFBB86FC"))
        textView.setTypeface(textView.typeface,Typeface.BOLD)
        textView.background=ContextCompat.getDrawable(this,
            R.drawable.selected_border_for_options)
    }

    private fun checkAnswer(){
        answered=true

        if(selectedAnswer == currentQuestion.answer){
            score++
            highlightSolution(selectedAnswer)
        }else{
            when(selectedAnswer){
                1 -> {
                    optionOne.background=ContextCompat.getDrawable(
                        this,R.drawable.wrong_answer_border_for_options
                    )
                }
                2 -> {
                    optionTwo.background=ContextCompat.getDrawable(
                        this,R.drawable.wrong_answer_border_for_options
                    )
                }
                3 -> {
                    optionThree.background=ContextCompat.getDrawable(
                        this,R.drawable.wrong_answer_border_for_options
                    )
                }
                4 -> {
                    optionFour.background=ContextCompat.getDrawable(
                        this,R.drawable.wrong_answer_border_for_options
                    )
                }
            }
        }
        checkButton.text="Next"
        showSolution()
    }

    private fun showSolution() {
        selectedAnswer=currentQuestion.answer
        highlightSolution(selectedAnswer)

    }

    private fun  highlightSolution(answer:Int){

        when (answer) {
            1 -> {
                optionOne.background = ContextCompat.getDrawable(
                    this, R.drawable.correct_answer_border_for_options
                )
            }

            2 -> {
                optionTwo.background = ContextCompat.getDrawable(
                    this, R.drawable.correct_answer_border_for_options
                )
            }

            3 -> {
                optionThree.background = ContextCompat.getDrawable(
                    this, R.drawable.correct_answer_border_for_options
                )
            }

            4 -> {
                optionFour.background = ContextCompat.getDrawable(
                    this, R.drawable.correct_answer_border_for_options
                )
            }
        }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.optionOneAnswer -> {
                selectedOption(optionOne, 1)
            }

            R.id.optionTwoAnswer -> {
                selectedOption(optionTwo, 2)
            }

            R.id.optionThreeAnswer -> {
                selectedOption(optionThree, 3)
            }

            R.id.optionFourAnswer -> {
                selectedOption(optionFour, 4)
            }

            R.id.checkBtn -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    showNextQuestion()
                }
                selectedAnswer=0
            }
        }
    }
}