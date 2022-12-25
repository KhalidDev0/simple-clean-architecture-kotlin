package com.example.khalidapp.viewModel.authScreen.registerFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(
    private val resultCallBack: RegisterResultCallBack,
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

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
            resultCallBack.onRegisterFailed("Please complete the registration!")
            return
        }

        //Register the user to the Firebase
        //if successful
        resultCallBack.onRegisterSuccess()
    }

    private fun checkValidity(): Boolean {

        if (email.value.isNullOrEmpty() || email.value.isNullOrBlank()) {
            return false
        } else if (password.value.isNullOrEmpty() || password.value.isNullOrBlank()) {

            return false
        } else if (name.value.isNullOrEmpty() || name.value.isNullOrBlank()) {

            return false
        } else if (age.value.isNullOrEmpty() || age.value.isNullOrBlank()) {

            return false
        } else if (gender.value.isNullOrEmpty() || gender.value.isNullOrBlank()) {

            return false
        }
        return true
    }

}

