package com.example.giphos.repository

import com.example.giphos.GiphySearchResponse

interface GiphyRepository {
    suspend fun getSearchGiphy(): GiphySearchResponse
    suspend fun getRandomGiphy(): GiphySearchResponse
}