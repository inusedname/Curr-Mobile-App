package dev.vstd.shoppingcart.dataMock.repository

sealed class Response<T: Any?> {
    data class Success<T>(val data: T) : Response<T>()
    data class Failed<T>(val message: String) : Response<T>()
}