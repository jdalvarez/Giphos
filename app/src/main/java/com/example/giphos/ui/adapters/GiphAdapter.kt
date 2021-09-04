package com.example.giphos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.giphos.GiphyViewHolder
import com.example.giphos.R
import com.example.giphos.data.model.Giph
import com.example.giphos.databinding.ItemGiphyBinding
import kotlin.reflect.KFunction1


class GiphyAdapter(
    private val onShortClick: (giphyUrl: String) -> Unit,
    private val onLongClick: KFunction1<Giph, Boolean>,
    val likeAnimation: (iV: LottieAnimationView, animation: Int) -> Boolean
) : RecyclerView.Adapter<GiphyViewHolder>() {

    private var giphys: MutableList<Giph> = mutableListOf()
    private val animation = R.raw.like_animate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val binding = ItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = GiphyViewHolder(binding, onShortClick, onLongClick, likeAnimation)
        return holder
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        holder.render(giphys[position],animation)
    }

    override fun getItemCount(): Int {
        return giphys.size
    }

   fun setData(data: List<Giph>?) {
        if (data != null) {
            val diffutilCallback= AdapterDiffUtilCallback(giphys, data)
            val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffutilCallback)
            giphys.clear()
            giphys.addAll(data)
            diffResult.dispatchUpdatesTo(this)
        }
    }



}