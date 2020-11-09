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
import kotlinx.android.synthetic.main.activity_sports.*

class Buisness : AppCompatActivity(), buissnessItemClicked {
    private lateinit var mAdapter: buissnessListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buisness)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchdata()
        mAdapter = buissnessListAdapter(this)
        recyclerView.adapter = mAdapter

    }

    private fun fetchdata() {
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=8e9bf0d5d77b4bfbb83b5aed220510e5"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,

            Response.Listener {
                val buissnessJsonArray = it.getJSONArray("articles")
                val buissnessArray = ArrayList<News>()
                for (i in 0 until buissnessJsonArray.length()) {
                    val buissnessJSONObject = buissnessJsonArray.getJSONObject(i)
                    val buissnessnews = News(
                        buissnessJSONObject.getString("title"),
                        buissnessJSONObject.getString("author"),
                        buissnessJSONObject.getString("url"),
                        buissnessJSONObject.getString("urlToImage"),

                        )
                    buissnessArray.add(buissnessnews)

                }
                mAdapter.updatedNews(buissnessArray)



            },
            Response.ErrorListener {


            }

        )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onbuissnessitemClicked(buisnessitem: News) {

        val builder =  CustomTabsIntent.Builder();
        val  customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(buisnessitem.url));

    }

}








