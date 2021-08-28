package com.example.giphos

import android.net.Uri
import android.view.View
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.giphos.databinding.ItemGiphyBinding
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController

class GiphyViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = ItemGiphyBinding.bind(view)

    fun render(giphys:GiphyItem){
        val uri: Uri = Uri.parse(giphys.images.previewGif?.url)
        val controller = Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true).build()
        binding.gif.controller = controller

    }
}