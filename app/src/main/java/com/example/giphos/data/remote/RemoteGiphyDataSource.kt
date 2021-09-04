package com.example.giphos.data.remote

import com.example.giphos.GiphyItem
import com.example.giphos.GiphyRandomResponse
import com.example.giphos.GiphySearchResponse
import com.example.giphos.repository.ApiService
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.Exception

class RemoteGiphyDataSource(private val apiService: ApiService) {
    suspend fun getSearchGiphy(query: String): Response<GiphySearchResponse> = apiService.getSearchGiphy(query = query)

    suspend fun getRandomGiphy(): Response<GiphySearchResponse> {
        val randomList: MutableList<GiphyItem> = mutableListOf()
        var lastError: Response<GiphyRandomResponse>? = null
        try {
            (1..20).forEach {
                val randomGiphyResponse = apiService.getRandomGiphy()
                if (randomGiphyResponse.isSuccessful) {
                    randomList.add(randomGiphyResponse.body()?.data!!)
                } else {
                    lastError = randomGiphyResponse
                }
            }
        } catch (e: Exception) {
            return Response.error(500, ResponseBody.create(MediaType.get("text/plain"), e.cause.toString()))
        }
        return if (randomList.size < 5 && lastError != null) {
            Response.error(lastError!!.code(), lastError!!.errorBody()!!)
        } else {
            Response.success(GiphySearchResponse(randomList.toList()))
        }
    }
}
