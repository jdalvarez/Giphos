package com.example.giphos

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getSearchGiphy(
        @Query("api_key") apiKey: String = AppConstants.API_KEY,
        @Query("q") query: String,
        @Query("limit") limit:Int=AppConstants.LIMIT
    ): Response<GiphySearchResponse>

    @GET("random")
    suspend fun random(@Query("api_key") apiKey: String = AppConstants.API_KEY): Response<GiphyRandomResponse>
}