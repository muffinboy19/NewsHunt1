package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.News


class NewsAdapter():RecyclerView.Adapter<NewsViewholder>() {

    private val list_of_items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_layout,parent,false)
        val viewHolder = NewsViewholder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list_of_items.size
    }

    override fun onBindViewHolder(holder: NewsViewholder, position: Int) {
        val currentItem = list_of_items[position]
        holder.tt.text = currentItem.title
        val image_ki_url = currentItem.imageUrl
        Glide.with(holder.itemView.context)
            .load(image_ki_url)
            .into(holder.ii)
    }
    fun updateNews(updatedIems: ArrayList<News>){
        list_of_items.clear()
        list_of_items.addAll(updatedIems)
        notifyDataSetChanged()
    }
}

class NewsItemClicked {

}

 public class NewsViewholder(view: View) :RecyclerView.ViewHolder(view){
val tt  = view.findViewById<TextView>(R.id.tt)
val ii  = view.findViewById<ImageView>(R.id.ii)


}
