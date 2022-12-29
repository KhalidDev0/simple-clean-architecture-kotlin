package com.example.khalidapp.presentation.auth.login.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.domain.auth.usecase.LoginUseCase
import com.example.khalidapp.presentation.common.utils.Resource
import com.google.api.ResourceProto.resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    val emailValidationError = MutableStateFlow<String?>(null)

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    val passwordValidationError = MutableStateFlow<String?>(null)

    val loginError = MutableSharedFlow<String>()

    private val _isLoading = MutableStateFlow(View.INVISIBLE)
    val isLoading = _isLoading.asStateFlow()

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome = _navigateToHome.asStateFlow()

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
            loginUseCase(email.value, password.value).onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.value = View.VISIBLE
                    }
                    is Resource.Success -> {
                        _isLoading.value = View.INVISIBLE
                        _navigateToHome.value = true
                    }
                    is Resource.Error -> {
                        loginError.emit(resource.apiError.errorMessage)
                        _isLoading.value = View.INVISIBLE

                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    private fun checkValidity(): Boolean {
        var isFormValid = true
        emailValidationError.value = null
        passwordValidationError.value = null

        if (_email.value.isEmpty() || _email.value.isBlank()) {
            emailValidationError.value = "Please enter a valid email address"
            isFormValid = false
        }
        if (_password.value.length < 6) {
            isFormValid = false
            if (_password.value.isEmpty() || _password.value.isBlank()){
                passwordValidationError.value = "Please enter a valid password"
            }
            else{
                passwordValidationError.value = "Password must be at least 6 characters"
            }
        }

        return isFormValid
    }
}