package com.example.giphos.data.remote

import com.example.giphos.GiphyRandomResponse
import com.example.giphos.GiphySearchResponse
import com.example.giphos.repository.ApiService
import retrofit2.Response

class GiphyDataSource(private val apiService:ApiService) {      //apiService tiene metodos que van a buscar info al servidor
    suspend fun getSearchGiphy(): Response<GiphySearchResponse> = apiService.getSearchGiphy(query = "")

    suspend fun getRandomGiphy(): Response<GiphyRandomResponse> = apiService.getRandomGiphy()
}