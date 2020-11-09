package com.example.dailykhabar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class technologyListAdapter(private val  listener: technologyClickListener):RecyclerView.Adapter<technologyViewHolder>() {
    private val technologyitems:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): technologyViewHolder {
        val technologyView =  LayoutInflater.from(parent.context).inflate(R.layout.items_technology,parent,false)
        val viewHolder = technologyViewHolder(technologyView)

        technologyView.setOnClickListener {
            listener.ontechnologyclicklistener(technologyitems[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: technologyViewHolder, position: Int) {

        val currennttechnologyitems = technologyitems[position]
        holder.technoogytitleView.text=currennttechnologyitems.title
        holder.technologyauthorView.text = currennttechnologyitems.author
        Glide.with(holder.itemView.context).load(currennttechnologyitems.imageUrl).into(holder.technologyimageView)

    }

    fun updatedNews(updatedNews: ArrayList<News>){
        technologyitems.clear()
        technologyitems.addAll(updatedNews)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return technologyitems.size
    }
}


class technologyViewHolder(technologyitems : View):RecyclerView.ViewHolder(technologyitems){

    val technoogytitleView : TextView = technologyitems.findViewById(R.id.techonolgytitle)
    val technologyauthorView : TextView = technologyitems.findViewById(R.id.technologyauthor)
    val technologyimageView : ImageView = technologyitems.findViewById(R.id.techonolgyimage)


}

interface technologyClickListener{
    fun ontechnologyclicklistener(technologyitems: News)
}