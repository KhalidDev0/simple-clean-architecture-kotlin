package com.example.khalidapp.data.authRepository

import com.example.khalidapp.common.utils.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun registerWithEmailAndPassword(email: String, password: String) : Resource<FirebaseUser>
    suspend fun login(email: String, password: String) : Resource<Boolean>
    suspend fun logout(): Resource<Boolean>
}