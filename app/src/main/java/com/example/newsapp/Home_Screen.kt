package com.example.newsapp

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import android.widget.ImageView
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Home_Screen : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val dataArray = listOf(Icon(R.drawable.war,"war"),Icon(R.drawable.goverment,"Government"),Icon(R.drawable.education,"Education"),Icon(
        R.drawable.health_care,"Health"),Icon(R.drawable.enviorment,"Nature"),Icon(R.drawable.economy,"Economy"),Icon(R.drawable.buisness__2_,"Business"),Icon(R.drawable.fashion,"Fashion"),Icon(R.drawable.entertainment,"Entertainment"),Icon(R.drawable.sports,"Sports"))
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var searchPlate :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val categori = findViewById<RecyclerView>(R.id.rc_categories)
        val News_ka_recylerView = findViewById<RecyclerView>(R.id.homeScreenNewsRecylerView)
        categori.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        categori.adapter=IconAdapter(dataArray)


        recyclerView = findViewById(R.id.homeScreenNewsRecylerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(this)
        recyclerView.adapter = newsAdapter

        fetchNewsData()

        val searchBarHomeScreen = findViewById<SearchView>(R.id.searchViewHomeScreen)
        val searchIconId = resources.getIdentifier("android:id/search_mag_icon",null,null)
        val searchIcon = searchBarHomeScreen.findViewById<ImageView>(searchIconId)
        searchIcon.setImageResource(R.drawable.searcg)
        searchBarHomeScreen.isIconifiedByDefault = false
        val searchPlateId = searchBarHomeScreen.context.resources.getIdentifier("android:id/search_src_text", null, null)
        searchPlate = searchBarHomeScreen.findViewById<EditText>(searchPlateId)
        searchPlate.hint = "Search..."
        searchIcon.setOnClickListener{
            performSearch()
        }

        searchPlate.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch()
                    return true
                }
                return false
            }
        })


        ////////////////////  nav bar/////////////////////////////////////////////
        bottomNavigationView = findViewById(R.id.bottom_navigationHomeScreen)
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








    }

    private fun performSearch() {
        val query = searchPlate.text.toString().trim()
        if (query.isNotEmpty()) {
            val intent = Intent(this, searchedNews::class.java)
            // Set the action to ACTION_SEARCH to indicate a search query
            intent.action = Intent.ACTION_SEARCH
            intent.putExtra(SearchManager.QUERY, query)
            startActivity(intent)
        }
    }

    private fun fetchNewsData() {
        NewsApiClient.getTopHeadlines(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    if (newsResponse != null) {
                        val articles = newsResponse.articles
                        newsAdapter.setData(articles)
                    } else {
                        Log.e("NewsAPI", "Response body is null")
                    }
                } else {
                    Log.e("NewsAPI", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("NewsAPI", "API call failed: ${t.message}")
            }
        })
    }

}




