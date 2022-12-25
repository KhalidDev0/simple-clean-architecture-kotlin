package com.example.khalidapp.viewModel.authScreen.registerFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel() : ViewModel() {

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
        if (!checkValidity()){
            return
        }

        //Register the user to the Firebase
        //if successful
        _navigateToHome.value = true
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
