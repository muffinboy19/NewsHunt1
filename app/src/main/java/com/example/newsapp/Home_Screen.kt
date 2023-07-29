package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView



class Home_Screen : AppCompatActivity() {


    private lateinit var news_ka_adapter:NewsAdapter
    private val dataArray = listOf(Icon(R.drawable.war),Icon(R.drawable.goverment),Icon(R.drawable.education),Icon(
        R.drawable.health_care),Icon(R.drawable.enviorment),Icon(R.drawable.economy),Icon(R.drawable.buisness__2_),Icon(R.drawable.fashion),Icon(R.drawable.entertainment),Icon(R.drawable.sports))
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val categori = findViewById<RecyclerView>(R.id.rc_categories)
        val News_ka_recylerView = findViewById<RecyclerView>(R.id.latestitem)
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
//
//        News_ka_recylerView.layoutManager= LinearLayoutManager(this)
        fetchData()
         news_ka_adapter = NewsAdapter()
        News_ka_recylerView.adapter = news_ka_adapter








    }


    private fun fetchData(){


        val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=ebbb3e0de4e74e7d8fc0a7d79aa0b750"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
            val NewsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for(i in 0 until NewsJsonArray.length()){
                    val newsJsonObject = NewsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                news_ka_adapter.updateNews(newsArray)


            },
            {

                Toast.makeText(this, it.printStackTrace().toString(),Toast.LENGTH_LONG)
            })
        MySingleTon.getInstance(this).addToRequestQueue(jsonObjectRequest)


    }



}
