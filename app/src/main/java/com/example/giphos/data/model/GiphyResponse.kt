package com.example.giphos

import com.google.gson.annotations.SerializedName

data class GiphySearchResponse (
    @SerializedName("data") var data:List<GiphyItem> = listOf()
)

data class GiphyRandomResponse (
    @SerializedName("data") var data:GiphyItem
)

class GiphyItem(
    @SerializedName("id")val id:String,
    @SerializedName("title")val title:String?,
    @SerializedName("images")val images:GiphyImages              //todos los giphs tienen por lo menos alguna imagen por eso es no nulo
)

class GiphyImages(
    @SerializedName("preview_gif") val previewGif:GiphyImage?         //puede que alguno no tenga esta propiedad
)

class GiphyImage(
    @SerializedName("height")val height:String,
    @SerializedName("url")val url:String,
    @SerializedName("width")val width:String
)