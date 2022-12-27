package com.example.khalidapp.data.repository

import com.example.khalidapp.presentation.common.utils.Resource
import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.data.model.ApiError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepositoryImp(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun registerWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (result.user == null) {
                Resource.Error(ApiError(0, "Registration failed"))
            } else {
                Resource.Success(result.user!!)
            }
        } catch (e: Exception) {
            Resource.Error(ApiError(0, "Unknown  error: ${e.message}"))
        }
    }

    override suspend fun login(email: String, password: String): Resource<Boolean> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(ApiError(0, "Unknown  error: ${e.message}"))
        }
    }

    override suspend fun logout(): Resource<Boolean> {
        return try {
            firebaseAuth.signOut()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(ApiError(0, "Unknown error: ${e.message}"))
        }
    }
}