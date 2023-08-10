package com.example.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchNewsService {
    @GET("everything")
    fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("q") query: String // Add this parameter for the custom search query
    ): Call<SearchNewsResponse>
}
