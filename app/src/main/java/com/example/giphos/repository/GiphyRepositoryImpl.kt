package com.example.giphos.repository

import com.example.giphos.GiphyEntity
import com.example.giphos.GiphyItem
import com.example.giphos.GiphySearchResponse
import com.example.giphos.data.local.GiphyDao
import com.example.giphos.data.model.Giph
import com.example.giphos.data.model.toGiph
import com.example.giphos.data.model.toGiphyEntity
import com.example.giphos.data.model.toListOfGiph
import com.example.giphos.data.remote.RemoteGiphyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response


class GiphyRepositoryImpl(
    private val remoteDataSource: RemoteGiphyDataSource,
    private val localDataSource: GiphyDao
) : GiphyRepository {
    override suspend fun getSearchGiphy(query: String): Response<List<Giph>> {
        val response = remoteDataSource.getSearchGiphy(query)
        val responseBody = response.body()
        return Response.success(responseBody?.toListOfGiph())
    }


    override suspend fun getRandomGiphy(): Response<List<Giph>> =
        Response.success(remoteDataSource.getRandomGiphy().body()?.toListOfGiph())

    //Room
    override fun getAllFavorites(): Flow<List<Giph>> {
        val flowListGiphyEntity = localDataSource.getAllFavorites()
        return flowListGiphyEntity.map { listOfGiphyEntity ->
            listOfGiphyEntity.map { giphyEntity ->
                giphyEntity.toGiph()
            }
        }
    }

    override suspend fun addToFavorites(giphy: Giph) = localDataSource.saveGiphy(giphy.toGiphyEntity())
    override suspend fun removeFromFavorites(giphy: Giph) =
        localDataSource.deletegiphy(giphy.toGiphyEntity())

}

