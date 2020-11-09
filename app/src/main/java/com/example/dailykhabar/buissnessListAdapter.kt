package com.example.dailykhabar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class buissnessListAdapter(private val listener: buissnessItemClicked):RecyclerView.Adapter<buissnessListViewHolder>() {
    private val buisnessitems:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): buissnessListViewHolder {

        val buissnessView =  LayoutInflater.from(parent.context).inflate(R.layout.items_buisness,parent,false)
        val viewwHolder = buissnessListViewHolder(buissnessView)
        buissnessView.setOnClickListener {
            listener.onbuissnessitemClicked(buisnessitems[viewwHolder.adapterPosition])
        }
        return  viewwHolder


    }

    override fun onBindViewHolder(holder: buissnessListViewHolder, position: Int) {
        val currentbuissnesitem = buisnessitems[position]
        holder.buissnesstitleView.text =currentbuissnesitem.title
        holder.buissnessauthorView.text= currentbuissnesitem.author
        Glide.with(holder.itemView.context).load(currentbuissnesitem.imageUrl).into(holder.buissnessimageView)

    }

    fun updatedNews(updatedNews : ArrayList<News>){
        buisnessitems.clear()
        buisnessitems.addAll(updatedNews)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return buisnessitems.size

    }
}

class buissnessListViewHolder(buisnessitem: View):RecyclerView.ViewHolder(buisnessitem){

    val buissnesstitleView :TextView = buisnessitem.findViewById(R.id.buissnesstitle)
    val buissnessauthorView :TextView = buisnessitem.findViewById(R.id.buissnessauthor)
    val buissnessimageView : ImageView = buisnessitem.findViewById(R.id.buissnessimage)

}

interface buissnessItemClicked{
    fun onbuissnessitemClicked(buisnessitem: News)
}