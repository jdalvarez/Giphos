package com.example.giphos.repository

import com.example.giphos.GiphyRandomResponse
import com.example.giphos.GiphySearchResponse
import com.example.giphos.data.remote.GiphyDataSource
import retrofit2.Response

//creo una instancia de datasource para poder acceder a sus metodos
class GiphyRepositoryImpl(private val dataSource: GiphyDataSource):GiphyRepository {
    override suspend fun getSearchGiphy(query: String): Response<GiphySearchResponse> = dataSource.getSearchGiphy(query)

    override suspend fun getRandomGiphy(): Response<GiphySearchResponse> = dataSource.getRandomGiphy()

}

