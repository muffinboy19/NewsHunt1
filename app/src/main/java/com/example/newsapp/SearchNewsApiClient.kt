package com.example.newsapp

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchNewsApiClient {



    private val BASE_URL = "https://newsapi.org/v2/"
    private val API_KEY =  "ebbb3e0de4e74e7d8fc0a7d79aa0b750"// Replace this with your NewsAPI.org API key
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val newsService: SearchNewsService = retrofit.create(SearchNewsService::class.java)

    fun getTopHeadlines(query: String, callback: Callback<SearchNewsResponse>) {
        val call = newsService.getNews("us", API_KEY)
        call.enqueue(callback)
    }

}

