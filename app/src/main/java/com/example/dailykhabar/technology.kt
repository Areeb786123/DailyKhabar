package com.example.dailykhabar

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.covid19_tracker.MySingleton
import kotlinx.android.synthetic.main.activity_sports.*

class technology : AppCompatActivity(), technologyClickListener {
    private lateinit var mAdapter: technologyListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technology)


        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchdata()
         mAdapter =technologyListAdapter(this)
        recyclerView.adapter =  mAdapter
    }


    private fun fetchdata(){
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=8e9bf0d5d77b4bfbb83b5aed220510e5"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val technologyJSONArray = it.getJSONArray("articles")
                val technologyArray = ArrayList<News>()
                for( i in 0 until technologyJSONArray.length()){
                    val technologyJSONObject = technologyJSONArray.getJSONObject(i)
                    val technologynews = News(
                        technologyJSONObject.getString("title"),
                        technologyJSONObject.getString("author"),
                        technologyJSONObject.getString("url"),
                        technologyJSONObject.getString("urlToImage"),
                    )
                    technologyArray.add(technologynews)
                }
                mAdapter.updatedNews(technologyArray)
            },

            Response.ErrorListener {  }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)


    }

    override fun ontechnologyclicklistener(technologyitems: News) {

        val builder =  CustomTabsIntent.Builder();
        val  customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(technologyitems.url));

    }
}