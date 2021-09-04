package com.example.giphos.repository

import com.example.giphos.data.model.Giph
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface GiphyRepository {
    suspend fun getSearchGiphy(query: String): Response<List<Giph>>
    suspend fun getRandomGiphy(): Response<List<Giph>>

    //Room
    fun getAllFavorites(): Flow<List<Giph>>
    suspend fun addToFavorites(giph: Giph)
    suspend fun removeFromFavorites(giphyItem: Giph)

}