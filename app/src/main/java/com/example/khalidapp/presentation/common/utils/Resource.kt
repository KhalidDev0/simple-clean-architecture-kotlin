package com.example.khalidapp.presentation.common.utils

import com.example.khalidapp.data.model.ApiError

sealed class Resource<T: Any> {
    data class Success<T: Any>(val data: T): Resource<T>()
    data class Error<T: Any>(val apiError: ApiError): Resource<T>()
    class Loading<T: Any>: Resource<T>()
}
