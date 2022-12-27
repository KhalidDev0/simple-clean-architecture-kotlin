package com.example.khalidapp.presentation.common.utils

import android.widget.EditText
import android.widget.RadioButton
import androidx.databinding.BindingAdapter

@BindingAdapter("emailValidation")
fun checkEmail(editText: EditText, email: String?){
    if(email?.isEmpty() == true|| email?.isBlank() == true){
        editText.error = "Email is required"
    }else{
        editText.error = null
    }
}

@BindingAdapter("passwordValidation")
fun checkPassword(editText: EditText, password: String?){
    if(password?.isEmpty() == true|| password?.isBlank() == true){
        editText.error = "Password is required"
    }
    else{
        editText.error = null
    }
}

@BindingAdapter("nameValidation")
fun checkName(editText: EditText, name: String?){
    if(name?.isEmpty() == true || name?.isBlank() == true) {
        editText.error = "Name is required"
    }
    else{
        editText.error = null
    }
}

@BindingAdapter("ageValidation")
fun checkAge(editText: EditText, age: String?){
    if(age?.isEmpty() == true|| age?.isBlank() == true) {
        editText.error = "Age is required"
    }
    else{
        editText.error = null
    }
}