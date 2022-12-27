package com.example.khalidapp.presentation.auth.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _navigateToHome = MutableLiveData(false)
    val navigateToHome: LiveData<Boolean> = _navigateToHome

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _age = MutableLiveData<String>()
    val age: LiveData<String> = _age

    private val _gender = MutableLiveData<String>()
    val gender: LiveData<String> = _gender

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
            val resource = authRepository.registerWithEmailAndPassword(
                email.value!!,
                password.value!!,
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
        val resource = userRepository
            .addUser(
                uid,
                User(
                    _email.value!!,
                    _name.value!!,
                    _age.value!!,
                    _gender.value!!,
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

        if (email.value.isNullOrEmpty() || email.value.isNullOrBlank()) {
            _email.value = ""
            isFormValid = false
        }
        if (password.value.isNullOrEmpty() || password.value.isNullOrBlank()) {
            _password.value = ""
            isFormValid = false
        }
        if (name.value.isNullOrEmpty() || name.value.isNullOrBlank()) {
            _name.value = ""
            isFormValid = false
        }
        if (age.value.isNullOrEmpty() || age.value.isNullOrBlank()) {
            _age.value = ""
            isFormValid = false
        }
        if (gender.value.isNullOrEmpty() || gender.value.isNullOrBlank()) {
            _gender.value = ""
            isFormValid = false
        }
        return isFormValid
    }

}

