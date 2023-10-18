package com.app.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.quizapp.activities.QuestionActivity
import com.app.quizapp.ui.theme.QuizAppTheme
import com.app.quizapp.utils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var nameET:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton=findViewById(R.id.startBtn)
        nameET=findViewById(R.id.name)

        startButton.setOnClickListener{

            if(nameET.text.isNotEmpty()){
                Intent(this@MainActivity,QuestionActivity::class.java).also{
                    intent.putExtra(Constants.USER_NAME,nameET.text.toString())
                    startActivity(it)
                    finish()
                }
            }else{
                Toast.makeText(this,"Please Enter a Name",Toast.LENGTH_SHORT).show()
            }

        }

    }
}

