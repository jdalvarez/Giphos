package com.example.giphos.presentation

import androidx.lifecycle.*
import com.example.giphos.GiphyItem
import com.example.giphos.data.model.Giph
import com.example.giphos.repository.GiphyRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repo: GiphyRepository) :
    ViewModel() { //usamos la interfaz GiphyRepository para implementar sus metodos

    val favoriteGiphyList = repo.getAllFavorites().asLiveData()

    fun deleteFromFavorites(giphy: Giph) = viewModelScope.launch() {
        repo.removeFromFavorites(giphy)
    }
}

//creamos el factory para ingresar el parametro repo en el constructor
class FavoritesViewModelFactory(private val repo: GiphyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GiphyRepository::class.java).newInstance(repo)
    }

}