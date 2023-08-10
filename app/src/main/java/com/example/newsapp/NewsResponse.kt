package com.example.newsapp

data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val title: String,
    val url :String,
    val urlToImage: String
)

