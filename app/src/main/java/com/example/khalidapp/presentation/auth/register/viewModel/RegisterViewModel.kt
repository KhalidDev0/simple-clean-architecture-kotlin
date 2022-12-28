package com.example.khalidapp.presentation.auth.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.usecase.AddUserUseCase
import com.example.khalidapp.domain.auth.usecase.RegisterUseCase
import com.example.khalidapp.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> = _gender

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
        this._gender.value = chosenGender
    }

    fun registerUser() {
        if (!checkValidity()) {
            return
        }

        //Register the user to the Firebase auth and Firestore
        viewModelScope.launch {
            val resource = registerUseCase(
                email.value,
                password.value,
            )

            when (resource) {
                is Resource.Success -> {
                    addUser(resource.data.uid)
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }

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

        if (email.value.isEmpty() || email.value.isBlank()) {
            _email.value = ""
            isFormValid = false
        }
        if (password.value.isEmpty() || password.value.isBlank()) {
            _password.value = ""
            isFormValid = false
        }
        if (name.value.isEmpty() || name.value.isBlank()) {
            _name.value = ""
            isFormValid = false
        }
        if (age.value.isEmpty() || age.value.isBlank()) {
            _age.value = ""
            isFormValid = false
        }
        if (gender.value.isEmpty() || gender.value.isBlank()) {
            _gender.value = ""
            isFormValid = false
        }
        return isFormValid
    }

}

