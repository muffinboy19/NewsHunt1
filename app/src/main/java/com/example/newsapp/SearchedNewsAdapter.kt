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
import com.squareup.picasso.Picasso

class SearchedNewsAdapter(private val context: Context) : RecyclerView.Adapter<SearchedNewsAdapter.SearchNewsViewHolder>() {
    private var newsList: MutableList<SearchArticle> = mutableListOf()

    fun setData(data: List<SearchArticle>) {
        newsList.clear()
        newsList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_layout, parent, false)
        return SearchNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchNewsViewHolder, position: Int) {
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

    fun setNewsList(newsList: List<SearchArticle>) {
        this.newsList = newsList.toMutableList()
        notifyDataSetChanged()
    }

    inner class SearchNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

