package com.example.newsapp

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


        loginButton.setOnClickListener {


            if (loginName.text.isEmpty() || loginPassword.text.isEmpty()) {
                Toast.makeText(this,
                    "Enter Credentials",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
}