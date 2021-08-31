package com.example.giphos.repository

import com.example.giphos.GiphyRandomResponse
import com.example.giphos.GiphySearchResponse
import retrofit2.Response

interface GiphyRepository {
    suspend fun getSearchGiphy(query: String): Response<GiphySearchResponse>
    suspend fun getRandomGiphy(): Response<GiphySearchResponse>
}