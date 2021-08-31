package com.example.giphos.data.local

import androidx.room.*
import com.example.giphos.GiphyEntity
import com.example.giphos.GiphyItem

@Dao
interface GiphyDao {
    @Query("SELECT * FROM giphyentity")
    suspend fun getAllGiphys(): List<GiphyItem>
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun saveGiphy(giphy:GiphyEntity)
    @Delete
    suspend fun deletegiphy(giphy: GiphyEntity)
}