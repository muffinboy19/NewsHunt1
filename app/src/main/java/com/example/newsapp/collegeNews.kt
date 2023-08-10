package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView

class collegeNews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_college_news)

        val searchBarCollegeNews = findViewById<SearchView>(R.id.searchViewCollegeNews)
        val searchIconId = resources.getIdentifier("android:id/search_mag_icon", null, null)
        val searchIcon = searchBarCollegeNews.findViewById<ImageView>(searchIconId)
        searchIcon.setImageResource(R.drawable.searcg)
        searchBarCollegeNews.isIconifiedByDefault = false
        val searchPlateId = searchBarCollegeNews.context.resources.getIdentifier(
            "android:id/search_src_text",
            null,
            null
        )
        val searchPlate = searchBarCollegeNews.findViewById<EditText>(searchPlateId)
        searchPlate.hint = "Search..."





        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigationCollegeNews)
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
}