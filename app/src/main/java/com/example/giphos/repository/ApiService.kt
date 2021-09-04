package com.example.giphos.repository

import com.example.giphos.application.AppConstants
import com.example.giphos.GiphyRandomResponse
import com.example.giphos.GiphySearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getSearchGiphy(
        @Query("api_key") apiKey: String = AppConstants.API_KEY,
        @Query("q") query: String = "a",
        @Query("limit") limit:Int= AppConstants.LIMIT
    ): Response<GiphySearchResponse>

    @GET("random")
    suspend fun getRandomGiphy(@Query("api_key") apiKey: String = AppConstants.API_KEY): Response<GiphyRandomResponse>
}


object RetrofiClient{
    val apiservice by lazy{
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}
