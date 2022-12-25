package com.example.khalidapp.viewModel.authScreen.registerFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory(private val resultCallBack: RegisterResultCallBack): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(resultCallBack) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}