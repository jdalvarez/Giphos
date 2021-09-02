package com.example.giphos.data.local

import com.example.giphos.GiphyEntity
import com.example.giphos.GiphyItem
import kotlinx.coroutines.flow.Flow

class LocalGiphyDataSource(private val giphyDao: GiphyDao) {
    fun getAllFavorites(): Flow<List<GiphyEntity>> = giphyDao.getAllFavorites()
    suspend fun addToFavorite(giphy:GiphyEntity) = giphyDao.saveGiphy(giphy)
    suspend fun removefromFavorite(giphy: GiphyEntity) = giphyDao.deletegiphy(giphy)
}