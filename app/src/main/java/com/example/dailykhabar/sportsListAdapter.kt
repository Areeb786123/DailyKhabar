package com.example.dailykhabar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class  sportsListAdapter(private val listener: sportsItemClicked):RecyclerView.Adapter<sportsListViewHolder>(){

   private  val sportsitems: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sportsListViewHolder {
        val sportsview = LayoutInflater.from(parent.context).inflate(R.layout.items_sports,parent,false)
       val  viewHolder = sportsListViewHolder(sportsview)

        sportsview.setOnClickListener{
            listener.onsportsItemClicked(sportsitems[viewHolder.adapterPosition])
        }
        return  viewHolder



    }

    override fun onBindViewHolder(holder: sportsListViewHolder, position: Int) {
        val currentsportsItems = sportsitems[position]
        holder.sportstitleView.text = currentsportsItems.title
        holder.sportsauthorview.text = currentsportsItems.author
        Glide.with(holder.itemView.context).load(currentsportsItems.imageUrl).into(holder.sportsimage)



    }


    fun upadtedNews(updatedNews: ArrayList<News>){
        sportsitems.clear()
        sportsitems.addAll(updatedNews)

        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return sportsitems.size
        

    }

}

class sportsListViewHolder(sportsitemView: View):RecyclerView.ViewHolder(sportsitemView){
    val sportstitleView: TextView = sportsitemView.findViewById(R.id.sportstitle)
    val sportsauthorview :TextView = sportsitemView.findViewById(R.id.sportsauthor)
    val sportsimage : ImageView = sportsitemView.findViewById(R.id.sportsimage)


}
interface sportsItemClicked{
    fun onsportsItemClicked(sportsitem: News)
}


