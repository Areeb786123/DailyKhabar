package com.example.dailykhabar

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.items_news.view.*
import org.w3c.dom.Text

class entertainmentListAdapter(private val listener: entertainmentItemClicked):RecyclerView.Adapter<entertainmentListViewHolder>() {

    private val entertainmentitems :ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): entertainmentListViewHolder {
        val entertainmentView = LayoutInflater.from(parent.context).inflate(R.layout.items_entertainment,parent,false)
        val viewHolder = entertainmentListViewHolder(entertainmentView)

        entertainmentView.setOnClickListener{
            listener.onentertainmentItemClicked(entertainmentitems[viewHolder.adapterPosition])
        }

        return  viewHolder


    }

    override fun onBindViewHolder(holder: entertainmentListViewHolder, position: Int) {
        val current_entertainmentitems  = entertainmentitems[position]
        holder.entertainmenttitleView.text =  current_entertainmentitems.title
        holder.entertainmentauthorView.text = current_entertainmentitems.author
        Glide.with(holder.itemView.context).load(current_entertainmentitems.imageUrl).into(holder.entertainmentimageView)

    }

    fun upadtedNews(updatedNews: ArrayList<News>){
        entertainmentitems.clear()
        entertainmentitems.addAll(updatedNews)

        notifyDataSetChanged()
    }




    override fun getItemCount(): Int {

        return entertainmentitems.size

    }
}

class entertainmentListViewHolder(entertainmentitems: View):RecyclerView.ViewHolder(entertainmentitems){
    val entertainmenttitleView :TextView =  entertainmentitems.findViewById(R.id.entertainmenttitle)
    val entertainmentauthorView : TextView = entertainmentitems.findViewById(R.id.entertainmentauthor)
    val entertainmentimageView :ImageView =  entertainmentitems.findViewById(R.id.entertainmentimage)


}

interface entertainmentItemClicked{
    fun onentertainmentItemClicked(entertainmentitems: News)
}

