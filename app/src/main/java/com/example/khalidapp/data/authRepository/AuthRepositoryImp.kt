package com.example.khalidapp.data.authRepository

import com.example.khalidapp.common.utils.Resource
import com.example.khalidapp.data.model.ApiError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepositoryImp(
    private val firebaseAuth : FirebaseAuth
): AuthRepository {

    override suspend fun registerWithEmailAndPassword(email: String, password: String) : Resource<FirebaseUser>{
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            if (result.user == null){
                Resource.Error(ApiError(0,"Registration failed"))
            }
            else {
                Resource.Success(result.user!!)
            }
        } catch (e: Exception) {
            Resource.Error(ApiError(0,"Unknown  error: ${e.message}"))
        }
    }

    override suspend fun login(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}