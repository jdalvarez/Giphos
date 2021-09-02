package com.example.giphos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class GiphySearchResponse(
    @SerializedName("data") var data: List<GiphyItem> = listOf()
)

data class GiphyRandomResponse(
    @SerializedName("data") var data: GiphyItem
)

class GiphyItem(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String?,
    @SerializedName("images") val images: GiphyImages              //todos los giphs tienen por lo menos alguna imagen por eso es no nulo
)

class GiphyImages(
    @SerializedName("preview_gif") val previewGif: GiphyImage,         //puede que alguno no tenga esta propiedad
    @SerializedName("original") val original: GiphyImage
)

class GiphyImage(
    @SerializedName("height") val height: String,
    @SerializedName("url") val url: String = "",
    @SerializedName("width") val width: String
)

//ROOM
@Entity
data class GiphyEntity(
    @PrimaryKey
    val id: String = "-1",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "preview_image_url")
    val previewImageUrl: String = "",
    @ColumnInfo(name = "original_image_url")
    val originalImageUrl: String = ""
)

fun GiphyItem.toGiphyEntity():GiphyEntity{
    return GiphyEntity(
        this.id,
        this.title.toString(),
        this.images.previewGif.url,
        this.images.original.url
    )
}