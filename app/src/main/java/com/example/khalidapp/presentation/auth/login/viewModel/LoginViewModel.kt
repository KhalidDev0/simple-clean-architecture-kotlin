package com.example.khalidapp.presentation.auth.login.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.domain.auth.usecase.LoginUseCase
import com.example.khalidapp.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _navigateToHome = MutableLiveData(false)
    val navigateToHome: LiveData<Boolean> = _navigateToHome

    fun setEmail(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        this._email.value = charSequence.toString()
    }

    fun setPassword(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        this._password.value = charSequence.toString()
    }

    fun loginUser() {

        if (!checkValidity()) {
            return
        }

        //Register the user to the Firebase auth and Firestore
        viewModelScope.launch {
            Log.d("TEST", "${_email.value}, ${_password.value}")
            val resource = loginUseCase(
                email.value!!,
                password.value!!,
            )

            Log.d("TEST", "${resource.toString()}")

            when (resource) {
                is Resource.Success -> {
                    _navigateToHome.value = true
                }
                is Resource.Error -> {
                    Log.d("TEST", resource.apiError.errorMessage)
                }
                is Resource.Loading -> {

                }
            }

        }
    }

    private fun checkValidity(): Boolean {
        var isFormValid = true

        if (email.value.isNullOrEmpty() || email.value.isNullOrBlank()) {
            _email.value = ""
            isFormValid = false
        }
        if (password.value.isNullOrEmpty() || password.value.isNullOrBlank()) {
            _password.value = ""
            isFormValid = false
        }
        return isFormValid
    }
}