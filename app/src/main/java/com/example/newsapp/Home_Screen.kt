package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home_Screen : AppCompatActivity() {


    ///***** LatesNews code*****/
    private lateinit var latestitem : RecyclerView
    private lateinit var kapa : NewsAdapter
    private lateinit var lists : List<NewsItem>
    private lateinit var newsAdapter: NewsAdapter


    ///*****Categorie code *****//
    private val dataArray = listOf(Icon(R.drawable.war),Icon(R.drawable.goverment),Icon(R.drawable.education),Icon(
        R.drawable.health_care),Icon(R.drawable.enviorment),Icon(R.drawable.economy),Icon(R.drawable.buisness__2_),Icon(R.drawable.fashion),Icon(R.drawable.entertainment),Icon(R.drawable.sports))

    private lateinit var searchViewBar : SearchView
    //***navigation bar *** code //
    private lateinit var bottomNavigationView: BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        ///***** LatesNews code*****/
        latestitem = findViewById(R.id.latestitem)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val apiService = retrofit.create(NewsApiService::class.java)
        val apiKey = "ebbb3e0de4e74e7d8fc0a7d79aa0b750"
        apiService.getTopHeadlines(apiKey= apiKey).enqueue(object :Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.isSuccessful){

                    val newsItem = response.body()?.articles?: emptyList()
                    showNews(newsItem)
                }
                else{
                    showError()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                showError()
            }
        })

        //searchViwecode*** //




        ///*****Categorie code *****//
        val categori = findViewById<RecyclerView>(R.id.rc_categories)
        categori.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        categori.adapter=IconAdapter(dataArray)









        ////////////////////  nav bar/////////////////////////////////////////////
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    // Handle home tab click
                    // Navigate to the corresponding fragment or activity
                    true
                }
                R.id.iiita -> {
                    // Handle dashboard tab click
                    // Navigate to the corresponding fragment or activity
                    true
                }
                else -> false
            }
        }




    }
    private fun showNews(newsItems: List<NewsItem>) {
        newsAdapter = NewsAdapter(newsItems)
        latestitem.layoutManager = LinearLayoutManager(this)
        latestitem.adapter = newsAdapter
    }

    private fun showError() {
        Toast.makeText(this, "Error fetching news", Toast.LENGTH_SHORT).show()
    }
}
