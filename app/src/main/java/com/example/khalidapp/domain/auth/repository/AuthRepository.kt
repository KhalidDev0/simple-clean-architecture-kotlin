package com.example.khalidapp.domain.auth.repository

import com.example.khalidapp.presentation.common.utils.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun register(email: String, password: String) : Resource<FirebaseUser>
    suspend fun login(email: String, password: String) : Resource<Boolean>
    suspend fun signOut(): Resource<Boolean>
}