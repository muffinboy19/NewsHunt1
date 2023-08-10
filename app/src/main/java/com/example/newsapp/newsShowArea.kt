package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class newsShowArea : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_show_area)
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true // Enable JavaScript if needed

        // Get the article URL from the intent
        val articleUrl = intent.getStringExtra("article_url")

        if (!articleUrl.isNullOrEmpty()) {
            // Load the article URL in the WebView
            webView.webViewClient = WebViewClient()
            webView.loadUrl(articleUrl)
        }
    }
}