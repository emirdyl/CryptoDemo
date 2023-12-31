package com.example.cryptodemo.util

sealed class ViewState<T> {
    class Loading<T> : ViewState<T>()
    class Success<T>(val data: T) : ViewState<T>()
    class Failed<T>(val throwable: Throwable) : ViewState<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(throwable: Throwable) = Failed<T>(throwable)
    }
}