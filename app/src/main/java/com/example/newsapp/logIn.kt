package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class logIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val loginName = findViewById<EditText>(R.id.loginName)
        val loginPassword = findViewById<EditText>(R.id.loginPassword)
        val forgotPassword = findViewById<TextView>(R.id.forgotPassword)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val loginRegister = findViewById<TextView>(R.id.loginRegister)



        loginButton.setOnClickListener {
            if (loginName.text.isEmpty() || loginPassword.text.isEmpty()) {
                Toast.makeText(
                    this,
                    "Enter Credentials",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(
                    this,
                    Home_Screen::class.java
                )
                startActivity(intent)
            }
            else{
                val intent = Intent(this,Home_Screen::class.java)
                startActivity(intent)
            }
        }
        forgotPassword.setOnClickListener {
            Toast.makeText(this, "fuck off gaurav", Toast.LENGTH_SHORT).show()
        }
        loginRegister.setOnClickListener {
            val intent = Intent(
                this,
                register_page::class.java
            )
            startActivity(intent)
        }

    }
}