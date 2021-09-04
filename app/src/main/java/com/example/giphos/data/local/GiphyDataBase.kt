package com.example.giphos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.giphos.GiphyEntity

@Database(entities = [GiphyEntity::class], version = 1)
abstract class GiphyDataBase:RoomDatabase() {
    abstract fun giphyDao(): GiphyDao

    companion object{
        private var INSTANCE: GiphyDataBase? = null

        fun getDatabase(context: Context): GiphyDataBase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                GiphyDataBase::class.java,
                "giphy_table"
            ).build()
            return INSTANCE!!
        }
    }
}