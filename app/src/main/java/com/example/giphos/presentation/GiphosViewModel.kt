package com.example.giphos.presentation

import androidx.lifecycle.*
import com.example.giphos.GiphyItem
import com.example.giphos.GiphySearchResponse
import com.example.giphos.core.Resource
import com.example.giphos.data.local.GiphyDao
import com.example.giphos.data.model.Giph
import com.example.giphos.repository.GiphyRepository
import com.example.giphos.toGiphyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

class GiphosViewModel(private val repo: GiphyRepository) :
    ViewModel() {

    private val _responseLiveData = MutableLiveData<Resource<List<Giph>?>>()
    val responseLiveData : LiveData<Resource<List<Giph>?>>
        get() = _responseLiveData

    fun addToFavorites(giphy: Giph) = viewModelScope.launch() {
        repo.addToFavorites(giphy)
    }

    fun fetch(query:String? = null) {
        viewModelScope.launch {
            _responseLiveData.value = Resource.Loading()

            var response: Response<List<Giph>>? = null
            if (!query.isNullOrEmpty()) {
                response = repo.getSearchGiphy(query)
            } else {
                response = repo.getRandomGiphy()
            }
            if (response.isSuccessful) {
                _responseLiveData.value = Resource.Success(response.body())
            } else {
                _responseLiveData.value = Resource.Failure(response.body())
            }
        }
    }



    fun fetchSearchGiphy(query: String? = null) =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            var response: Response<List<Giph>>? = null
            if (!query.isNullOrEmpty()) {
                response = repo.getSearchGiphy(query)
            } else {
                response = repo.getRandomGiphy()
            }
            if (response.isSuccessful) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Failure(response))
            }
        }
}


class GiphyViewModelFactory(private val repo: GiphyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GiphyRepository::class.java).newInstance(repo)
    }

}