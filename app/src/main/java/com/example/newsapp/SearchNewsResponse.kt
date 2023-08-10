package com.example.newsapp

import android.icu.text.CaseMap.Title

class SearchNewsResponse (
    val articles: List<SearchArticle>
)
data class SearchArticle(
    val title: String,
    val url:String,
    val urlToImage: String
)