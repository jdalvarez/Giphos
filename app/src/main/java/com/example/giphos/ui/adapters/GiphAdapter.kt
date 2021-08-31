package com.example.giphos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giphos.GiphyItem
import com.example.giphos.GiphyViewHolder
import com.example.giphos.databinding.ItemGiphyBinding



class GiphyAdapter(
    private val onShortClick:(giphyUrl:String) -> Unit,
    private val onLongClick: (giphyUrl:String) -> Boolean
):RecyclerView.Adapter<GiphyViewHolder>() {

    var giphys:MutableList<GiphyItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val binding = ItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = GiphyViewHolder(binding,onShortClick,onLongClick)
        return holder
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        holder.render(giphys[position])
    }

    override fun getItemCount(): Int {
        return giphys.size
    }

    fun setData(data:List<GiphyItem>?){
        giphys.clear()
        if (data != null) {
            giphys.addAll(data)
        }
        notifyDataSetChanged()
    }

}