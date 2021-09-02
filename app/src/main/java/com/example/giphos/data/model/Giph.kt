package com.example.giphos.data.model

import androidx.room.ColumnInfo
import com.example.giphos.GiphyEntity
import com.example.giphos.GiphyItem
import com.example.giphos.GiphySearchResponse

data class Giph(val id:String, val title:String, val previewImageUrl: String, val originalImageUrl: String)



fun GiphyEntity.toGiph():Giph {
    return Giph(
        this.id,
       this.title,
        this.previewImageUrl,
        this.originalImageUrl
    )
}

fun GiphyItem.toGiph(): Giph {
    return Giph(this.id, this.title ?: "", this.images.previewGif.url, this.images.original.url)
}

fun GiphySearchResponse.toListOfGiph(): List<Giph> {
    return this.data.map { it.toGiph() }
}

fun Giph.toGiphyEntity(): GiphyEntity = GiphyEntity(this.id, this.title, this.previewImageUrl, this.originalImageUrl)

