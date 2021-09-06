package com.example.giphos.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphos.GiphyItem

class GiphosViewModel:ViewModel() {
    val giphyModel = MutableLiveData<GiphyItem>()


}