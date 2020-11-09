package com.example.dailykhabar

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.covid19_tracker.MySingleton
import kotlinx.android.synthetic.main.activity_health.*

class health : AppCompatActivity(), healthItemClicked {
    private lateinit var mAdapter : healthListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health)

        recyclerView.layoutManager= LinearLayoutManager(this)
        fetchdata()
        mAdapter= healthListAdapter(this)
        recyclerView.adapter= mAdapter


    }
    private  fun fetchdata(){

        val url = "https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=8e9bf0d5d77b4bfbb83b5aed220510e5"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val healthJsonArray   =  it.getJSONArray("articles")
                val healthArray = ArrayList<News>()
                for(i in 0 until healthJsonArray.length()) {
                    val healthJsonObject = healthJsonArray.getJSONObject(i)
                    val healthnews = News(
                        healthJsonObject.getString("title"),
                        healthJsonObject.getString("author"),
                        healthJsonObject.getString("url"),
                        healthJsonObject.getString("urlToImage"),
                    )

                    healthArray.add(healthnews)
                }
                mAdapter.updatedNews(healthArray)




            },

            Response.ErrorListener {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

        }

    override fun onhealthitemClicked(healthitem: News) {

        val builder =  CustomTabsIntent.Builder();
        val  customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(healthitem.url));

    }


}
