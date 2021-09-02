package com.example.giphos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.giphos.GiphyEntity

@Database(entities = [GiphyEntity::class], version = 1)
abstract class GiphyDataBase:RoomDatabase() {  //hago la clase abstracta para poder acceder a sus metodos
    abstract fun giphyDao(): GiphyDao

    companion object{       //creamos un "singleton" para tener una unica instancia de room
        private var INSTANCE: GiphyDataBase? = null     //nula pq aun no esta inicialiada

        fun getDatabase(context: Context): GiphyDataBase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                GiphyDataBase::class.java,
                "giphy_table"
            ).build()
            return INSTANCE!!       //por el elvis operator podemos asegurar que no es nula
        }
    }
}