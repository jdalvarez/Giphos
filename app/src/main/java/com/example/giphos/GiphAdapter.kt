package com.example.giphos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GiphAdapter():RecyclerView.Adapter<GiphyViewHolder>() {
    var giphys:MutableList<GiphyItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GiphyViewHolder(layoutInflater.inflate(R.layout.item_giphy,parent,false))
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        holder.render(giphys[position])
    }

    override fun getItemCount(): Int {
        return giphys.size
    }

    fun setData(data:List<GiphyItem>){
        giphys.clear()
        giphys.addAll(data)
        notifyDataSetChanged()
    }

}