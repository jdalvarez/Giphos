package com.example.giphos.data.local

import androidx.room.*
import com.example.giphos.GiphyEntity
import com.example.giphos.GiphyItem
import kotlinx.coroutines.flow.Flow

@Dao
interface GiphyDao {
    @Query("SELECT * FROM giphyentity")
    fun getAllFavorites(): Flow<List<GiphyEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun saveGiphy(giphy:GiphyEntity)
    @Delete
    suspend fun deletegiphy(giphy: GiphyEntity)
}