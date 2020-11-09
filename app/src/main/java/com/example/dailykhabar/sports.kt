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
import kotlinx.android.synthetic.main.activity_main.*

class sports : AppCompatActivity(), sportsItemClicked {
    private lateinit var mAdapter : sportsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchdata()
         mAdapter = sportsListAdapter(this)
        recyclerView.adapter = mAdapter

    }

    private fun fetchdata() {
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=8e9bf0d5d77b4bfbb83b5aed220510e5"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val sportsJsonArray = it.getJSONArray("articles")
                val sportsArray =ArrayList<News>()
                for( i in 0 until  sportsJsonArray.length()){
                    val sportsJsonObject = sportsJsonArray.getJSONObject(i)
                    val sportsnews =News(
                        sportsJsonObject.getString("title"),
                        sportsJsonObject.getString("author"),
                        sportsJsonObject.getString("url"),
                        sportsJsonObject.getString("urlToImage")

                    )
                    sportsArray.add(sportsnews)
                }

                mAdapter.upadtedNews(sportsArray)


            },
            Response.ErrorListener {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override  fun onsportsItemClicked(sportsitem: News){
        val builder =  CustomTabsIntent.Builder();
        val  customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(sportsitem.url));
    }

}









