package com.example.giphos.repository

import com.example.giphos.GiphySearchResponse
import com.example.giphos.data.remote.GiphyDataSource

//creo una instancia de datasource para poder acceder a sus metodos
class GiphyRepositoryImpl(private val dataSource: GiphyDataSource):GiphyRepository {
    override suspend fun getSearchGiphy(): GiphySearchResponse = dataSource.getSearchGiphy()

    override suspend fun getRandomGiphy(): GiphySearchResponse =dataSource.getRandomGiphy()
}