package com.example.newsapp

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

class searchedNews : AppCompatActivity() {
    private lateinit var searchedNewsRecyclerView: RecyclerView
    private lateinit var searchedNewsAdapter: SearchedNewsAdapter
    private lateinit var bottomNavigationView: BottomNavigationView
    private val BASE_URL = "https://newsapi.org/v2/"
    private  var apiKey: String = "ebbb3e0de4e74e7d8fc0a7d79aa0b750"
    private  var country: String = "us"
    private var searchQuery: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searched_news)

        // this code here is reciving the query which is passed from the homes_screen
        val intent = intent
        if (Intent.ACTION_SEARCH == intent.action) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY) ?: ""
            // Initialize RecyclerView
            searchedNewsRecyclerView = findViewById(R.id.searchedNewsRecylerView)
            searchedNewsRecyclerView.layoutManager = LinearLayoutManager(this)
            searchedNewsAdapter = SearchedNewsAdapter(this)
            searchedNewsRecyclerView.adapter = searchedNewsAdapter

            // Fetch news based on the provided parameters
            fetchNewsData( apiKey, searchQuery)
        }

        bottomNavigationView = findViewById(R.id.bottom_navigationSearchedNews)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    val intent = Intent(this,Home_Screen::class.java)
                    startActivity(intent)
                    true
                }
                R.id.iiita -> {
                    val intent = Intent(this,collegeNews::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }


        /// search Bar code
        val searchBarSearchNews = findViewById<SearchView>(R.id.searchBarSearchedNews)
        val searchIconId = resources.getIdentifier("android:id/search_mag_icon", null, null)
        val searchIcon = searchBarSearchNews.findViewById<ImageView>(searchIconId)
        searchIcon.setImageResource(R.drawable.searcg)
        searchBarSearchNews.isIconifiedByDefault = false
        val searchPlateId = searchBarSearchNews.context.resources.getIdentifier(
            "android:id/search_src_text",
            null,
            null
        )
        val searchPlate = searchBarSearchNews.findViewById<EditText>(searchPlateId)
        searchPlate.hint = "Search..."
        searchBarSearchNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                fetchNewsData(apiKey,query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                fetchNewsData(apiKey,newText)
                return true
            }
        })




    }


    private fun fetchNewsData( apiKey: String, searchQuery: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SearchNewsService::class.java)

        val call = service.getNews( apiKey, searchQuery ?: "")


        if (call != null) {
            call.enqueue(object : retrofit2.Callback<SearchNewsResponse> {
                override fun onResponse(call: Call<SearchNewsResponse>, response: retrofit2.Response<SearchNewsResponse>) {
                    if (response.isSuccessful) {
                        val newsResponse = response.body()
                        val SearchNewsList = newsResponse?.articles ?: emptyList()

                        // Display news titles and images or do whatever you want with the data
                        for (SearchnewsArticle in SearchNewsList) {
                            val title = SearchnewsArticle.title
                            val imageUrl = SearchnewsArticle.urlToImage
                        }
                        searchedNewsAdapter.setNewsList(SearchNewsList)

                    } else {
                        Log.d("SearchNewsAPI", "Response: ${response.isSuccessful}")
                    }
                }

                override fun onFailure(call: Call<SearchNewsResponse>, t: Throwable) {
                    Log.e("SearchNewsAPI", "API call failed: ${t.message}")
                }
            })
        }

    }






}

















