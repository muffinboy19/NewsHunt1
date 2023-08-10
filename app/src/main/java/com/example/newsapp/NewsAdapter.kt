package com.example.newsapp

import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.content.Context
import android.content.Intent
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.text.TextWatcher
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val newsList: MutableList<Article> = mutableListOf()

    fun setData(data: List<Article>) {
        newsList.clear()
        newsList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_layout, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        val maxLength = 20
        val truncatedTitle = if (newsItem.title.length > maxLength) {
            newsItem.title.substring(0, maxLength) + "..."  // Append "..." to indicate truncation
        } else {
            newsItem.title
        }
        holder.titleTextView.text = newsItem.title
        Glide.with(context)
            .load(newsItem.urlToImage)
            .transform(RoundedCorners(28)) // Apply rounding transformation with 16dp radius
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTopNews)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewTopNews)
        init {
            itemView.setOnClickListener {
                val articleUrl = newsList[adapterPosition].url
                val intent = Intent(itemView.context, newsShowArea::class.java)
                intent.putExtra("article_url", articleUrl)
                itemView.context.startActivity(intent)
            }
        }

    }



}
