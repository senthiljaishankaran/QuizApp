package com.app.quizapp.utils

import com.app.quizapp.R
import com.app.quizapp.model.Question

object Constants {

    const val USER_NAME="user_name"
    const val SCORE="correct_answers"
    const val TOTAL_QUESTIONS="total_questions"


    fun getQuestions():MutableList<Question>{

         val questions= mutableListOf<Question>()
           val question1=Question(
               1,"What is the Name of the flag", R.drawable.india,"India","Italy","Ireland","Indonesia",1
           )
           val question2=Question(
               2,"What is the Name of the flag", R.drawable.australia,"America","Afganistan","Australia","Austria",3
           )
           val question3=Question(
               1,"What is the Name of the flag", R.drawable.japan,"Jakarda","Japan","china","Korea",2
           )
           val question4=Question(
               1,"What is the Name of the flag", R.drawable.brazil,"Bangladesh","Bermuda","Bangkok","Brazil",4
           )
           questions.add(question1)
           questions.add(question2)
           questions.add(question3)
           questions.add(question4)

        return questions
    }
}
