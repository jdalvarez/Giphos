package com.example.giphos.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.giphos.GiphyItem
import com.example.giphos.GiphySearchResponse
import com.example.giphos.core.Resource
import com.example.giphos.repository.GiphyRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class GiphosViewModel(private val repo: GiphyRepository) :
    ViewModel() { //usamos la interfaz GiphyRepository para implementar sus metodos

    fun fetchSearchGiphy(query: String? = null) =
        liveData(Dispatchers.IO) {//Esta funcion es la que sera llamada desde la interfaz grafica
            emit(Resource.Loading()) //con el estado de carga avisamos al usuario que se esta haciendo una llamada al servidor

            var response: Response<GiphySearchResponse>? = null
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

//creamos el factory para ingresar el parametro repo en el constructor
class GiphyViewModelFactory(private val repo: GiphyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GiphyRepository::class.java).newInstance(repo)
    }

}