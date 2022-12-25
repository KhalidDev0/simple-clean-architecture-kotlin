package com.example.khalidapp.viewModel.authScreen.registerFragment

interface RegisterResultCallBack {
    fun onRegisterSuccess()
    fun onRegisterFailed(message: String)
}