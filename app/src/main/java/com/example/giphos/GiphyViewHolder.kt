package com.example.giphos

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.giphos.databinding.ItemGiphyBinding
import com.example.giphos.ui.OnClickListener
import com.facebook.drawee.backends.pipeline.Fresco

class GiphyViewHolder(
    private val itemBinding: ItemGiphyBinding,
    val onShortClick:(giphyUrl:String) -> Unit,
    val onLongClick: (giphyUrl:String) -> Boolean):RecyclerView.ViewHolder(itemBinding.root) {
    fun render(giphy:GiphyItem){
        itemBinding.giphyCard.setOnClickListener {onShortClick(giphy.images.original?.url.toString())}
        itemBinding.giphyCard.setOnLongClickListener { onLongClick(giphy.images.previewGif?.url.toString()) }
        val uri: Uri = Uri.parse(giphy.images.previewGif?.url)
        val controller = Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true).build()
        itemBinding.gif.controller = controller
    }
}