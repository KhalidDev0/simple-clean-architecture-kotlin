package com.example.khalidapp.data.authRepository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepositoryImp(
    private val firebaseAuth : FirebaseAuth
): AuthRepository {

    override suspend fun registerWithEmailAndPassword(email: String, password: String) : FirebaseUser?{
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun login(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}