package com.example.giphos.core

import java.lang.Exception

sealed class Resource <out T> {     //retorna 3 estados
    class Loading<out T>: Resource<T>()
    data class Success<out T>(val data: T):Resource<T>()
    data class Failure<out T>(val data: T):Resource<T>()
}