package com.example.khalidapp.presentation.auth.register.viewModel

import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.usecase.AddUserUseCase
import com.example.khalidapp.domain.auth.usecase.RegisterUseCase
import com.example.khalidapp.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    val emailValidationError = MutableStateFlow<String?>(null)

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome

    private val _registerError = MutableSharedFlow<String>()
    val registerError = _registerError.asSharedFlow()

    private val _isLoading = MutableStateFlow(View.INVISIBLE)
    val isLoading = _isLoading.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password
    val passwordValidationError = MutableStateFlow<String?>(null)

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name
    val nameValidationError = MutableStateFlow<String?>(null)

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age
    val ageValidationError = MutableStateFlow<String?>(null)

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> = _gender
    val genderValidationError = MutableStateFlow<String?>(null)

    fun setEmail(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        this._email.value = charSequence.toString()
    }

    fun setPassword(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        this._password.value = charSequence.toString()
    }

    fun setName(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        this._name.value = charSequence.toString()
    }

    fun setAge(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        this._age.value = charSequence.toString()
    }

    fun setGender(chosenGender: String) {
        genderValidationError.value = null
        this._gender.value = chosenGender
    }

    fun registerUser() {
        if (!checkValidity()) {
            return
        }

        //Register the user to the Firebase auth and Firestore
        viewModelScope.launch {
           registerUseCase(email.value, password.value).onEach { resource ->
               when (resource) {
                   is Resource.Loading -> {
                       _isLoading.value = View.VISIBLE
                   }
                   is Resource.Success -> {
                       addUser(resource.data.uid)
                   }
                   is Resource.Error -> {
                       _registerError.emit(resource.apiError.errorMessage)
                       _isLoading.value = View.INVISIBLE

                   }
               }
           }.launchIn(viewModelScope)
        }
    }

    private suspend fun addUser(uid: String) {
        val resource = addUserUseCase(
            uid,
            User(
                _email.value,
                _name.value,
                _age.value,
                _gender.value,
            )
        )

        when (resource) {
            is Resource.Success -> {
                _isLoading.value = View.INVISIBLE
                _navigateToHome.value = true
            }
            is Resource.Error -> {

            }
            is Resource.Loading -> {

            }
        }
    }

    private fun checkValidity(): Boolean {
        var isFormValid = true
        emailValidationError.value = null
        passwordValidationError.value = null
        nameValidationError.value = null
        ageValidationError.value = null
        genderValidationError.value = null

        if (email.value.isEmpty() || email.value.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
            emailValidationError.value = "Please enter a valid email"
            isFormValid = false
        }
        if (password.value.isEmpty() || password.value.isBlank() || password.value.length < 6) {
            passwordValidationError.value = "Password must be at least 6 characters"
            isFormValid = false
        }
        if (name.value.isEmpty() || name.value.isBlank()) {
            nameValidationError.value = "Please enter your name"
            isFormValid = false
        }
        if (age.value.isEmpty() || age.value.isBlank()) {
            ageValidationError.value = "Please enter your age"
            isFormValid = false
        }
        if (gender.value.isEmpty() || gender.value.isBlank()) {
            genderValidationError.value = "Please enter your gender"
            isFormValid = false
        }
        return isFormValid
    }

}

