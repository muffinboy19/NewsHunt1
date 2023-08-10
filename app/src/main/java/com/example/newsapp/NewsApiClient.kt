package com.example.newsapp

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiClient {
    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val API_KEY =  "ebbb3e0de4e74e7d8fc0a7d79aa0b750"// Replace this with your NewsAPI.org API key

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val newsService: NewsService = retrofit.create(NewsService::class.java)

    fun getTopHeadlines(callback: Any) {
        val call = newsService.getTopHeadlines("us", API_KEY)
        call.enqueue(callback as Callback<NewsResponse>)
    }
}
