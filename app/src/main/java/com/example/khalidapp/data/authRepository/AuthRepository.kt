package com.example.khalidapp.data.authRepository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun registerWithEmailAndPassword(email: String, password: String) : FirebaseUser?
    suspend fun login(email: String, password: String)
    suspend fun logout()
}