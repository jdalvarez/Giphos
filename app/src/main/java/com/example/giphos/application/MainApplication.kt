package com.example.giphos.application

import android.app.Application
import com.example.giphos.data.local.GiphyDataBase
import com.example.giphos.data.remote.RemoteGiphyDataSource
import com.example.giphos.repository.GiphyRepository
import com.example.giphos.repository.GiphyRepositoryImpl
import com.example.giphos.repository.RetrofiClient
import com.facebook.drawee.backends.pipeline.Fresco

class MainApplication:Application() {

    val database by lazy { GiphyDataBase.getDatabase(this) }
    val localDataSource by lazy { database.giphyDao() }
    // We dont need lazy for remote data source, but we do it for consistency
    val remoteDataSource by lazy { RemoteGiphyDataSource(RetrofiClient.apiservice) }
    val repository by lazy { GiphyRepositoryImpl(remoteDataSource, localDataSource) }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }


}