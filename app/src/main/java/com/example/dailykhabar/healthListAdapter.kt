package com.example.dailykhabar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.awt.font.TextAttribute

class healthListAdapter(private val listener: healthItemClicked ):RecyclerView.Adapter<healthListViewwHolder>() {
    private val healthitems: ArrayList<News> =  ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): healthListViewwHolder {
        val healthview = LayoutInflater.from(parent.context).inflate(R.layout.items_health,parent,false)
        val viewHolder = healthListViewwHolder(healthview)

        healthview.setOnClickListener {
            listener.onhealthitemClicked(healthitems[viewHolder.adapterPosition])
        }

        return viewHolder

    }

    override fun onBindViewHolder(holder: healthListViewwHolder, position: Int) {

        val cuurenthealthitems = healthitems[position]
        holder.healthtitleView.text = cuurenthealthitems.title
        holder.healthauthorView.text = cuurenthealthitems.author
        Glide.with(holder.itemView.context).load(cuurenthealthitems.imageUrl).into(holder.healthImageView)

    }

    fun updatedNews(updatedNews: ArrayList<News>){
        healthitems.clear()
        healthitems.addAll(updatedNews)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return healthitems.size

    }



}

class healthListViewwHolder(healthitemView: View):RecyclerView.ViewHolder(healthitemView){

    val healthtitleView: TextView = healthitemView.findViewById(R.id.healthtitle)
    val healthauthorView :TextView = healthitemView.findViewById(R.id.healthauthor)
    val healthImageView : ImageView =  healthitemView.findViewById(R.id.healthimage)

}

interface healthItemClicked{
    fun onhealthitemClicked(healthitem : News)
}