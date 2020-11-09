package com.example.dailykhabar

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.covid19_tracker.MySingleton
import kotlinx.android.synthetic.main.activity_entertainment2.*

class entertainment : AppCompatActivity(), entertainmentItemClicked {
    private lateinit var mAdapter: entertainmentListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entertainment2)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchdata()
        mAdapter = entertainmentListAdapter(this)
        recyclerView.adapter = mAdapter


    }

    private  fun fetchdata(){
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=8e9bf0d5d77b4bfbb83b5aed220510e5"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val entertainmentJsonArray = it.getJSONArray("articles")
                val entertainmentArray   =  ArrayList<News>()
                for( i in 0 until entertainmentJsonArray.length()){
                    val entertainmentJSONObject =  entertainmentJsonArray.getJSONObject(i)
                    val entertainmentnews = News(

                        entertainmentJSONObject.getString("title"),
                        entertainmentJSONObject.getString("author"),
                        entertainmentJSONObject.getString("url"),
                        entertainmentJSONObject.getString("urlToImage")

                    )
                    entertainmentArray.add(entertainmentnews)
                }
                mAdapter.upadtedNews(entertainmentArray)


            },

            Response.ErrorListener {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    override  fun onentertainmentItemClicked(entertainmentitems: News){
        val builder =  CustomTabsIntent.Builder();
        val  customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(entertainmentitems.url));
    }


}