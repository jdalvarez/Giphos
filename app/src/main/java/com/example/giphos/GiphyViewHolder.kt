package com.example.giphos

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.giphos.data.model.Giph
import com.example.giphos.databinding.ItemGiphyBinding
import com.example.giphos.ui.OnClickListener
import com.facebook.drawee.backends.pipeline.Fresco

class GiphyViewHolder(
    private val itemBinding: ItemGiphyBinding,
    val onShortClick: (giphyUrl: String) -> Unit,
    val onLongClick: (giphy: Giph) -> Boolean,
    val likeAnimation: (iV: LottieAnimationView, animation: Int) -> Boolean
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun render(giph: Giph, animation: Int) {
        itemBinding.giphyCard.setOnClickListener { onShortClick(giph.previewImageUrl) }
        itemBinding.giphyCard.setOnLongClickListener {
            onLongClick(giph)
            likeAnimation(itemBinding.likeIv, animation)
        }

        val uri: Uri = Uri.parse(giph.previewImageUrl)
        val controller =
            Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true).build()
        itemBinding.gif.controller = controller
    }

}