    package com.example.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.VideoView

    class  splashScreen : AppCompatActivity() {
    private lateinit var vox : VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val splashScreenDurationMs = 3000 // 2 seconds (adjust as needed)
        Handler().postDelayed({
            // Start the main activity
            val intent = Intent(this, Home_Screen::class.java)
            startActivity(intent)

            // Finish the splash screen activity so that the user cannot go back to it
            finish()
        }, splashScreenDurationMs.toLong())
    }


}