package com.example.newsapp

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
class MySingleTon constructor(context: Context){

    companion object{
        private var INSTANCE : MySingleTon? =  null
        fun getInstance(context: Context) =
            INSTANCE?: synchronized(this){
                INSTANCE?:MySingleTon(context).also{
                    INSTANCE = it
                }
            }
    }
    private val requestQueue : RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
    fun<T> addToRequestQueue(req:Request<T>){
        requestQueue.add(req)
    }
}