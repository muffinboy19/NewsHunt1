package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class register_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val registerUser = findViewById<EditText>(R.id.registerUser)
        val registerPassword = findViewById<EditText>(R.id.registerPassword)
        val registerRePassword = findViewById<EditText>(R.id.registerRePassword)

        registerButton.setOnClickListener {
            if(registerPassword.text.isEmpty() || registerRePassword.text.isEmpty() || registerUser.text.isEmpty()){
                Toast.makeText(this, "Is This A Joke", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(
                    this,
                    Home_Screen::class.java
                )
                startActivity(intent)
            }
        }

    }
}