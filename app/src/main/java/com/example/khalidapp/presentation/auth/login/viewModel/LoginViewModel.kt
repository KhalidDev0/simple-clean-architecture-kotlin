package com.example.khalidapp.presentation.auth.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.domain.auth.usecase.LoginUseCase
import com.example.khalidapp.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome

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
            _isLoading.value = true
            val resource = loginUseCase(
                email.value,
                password.value,
            )
            _isLoading.value = false
            when (resource) {
                is Resource.Success -> {
                    _navigateToHome.value = true
                }
                is Resource.Error -> {
                }
                is Resource.Loading -> {

                }
            }

        }
    }

    private fun checkValidity(): Boolean {
        var isFormValid = true

        if (email.value.isEmpty() || email.value.isBlank()) {
            _email.value = ""
            isFormValid = false
        }
        if (password.value.isEmpty() || password.value.isBlank()) {
            _password.value = ""
            isFormValid = false
        }
        return isFormValid
    }
}