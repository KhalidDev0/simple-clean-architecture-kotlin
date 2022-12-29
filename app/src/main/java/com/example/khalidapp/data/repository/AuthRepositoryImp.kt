package com.example.khalidapp.data.repository

import com.example.khalidapp.presentation.common.utils.Resource
import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.data.model.ApiError
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.*
import kotlinx.coroutines.tasks.await

class AuthRepositoryImp(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun register(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (result.user == null) {
                Resource.Error(ApiError(0, "Registration failed"))
            } else {
                Resource.Success(result.user!!)
            }
        }
        catch(e: FirebaseAuthWeakPasswordException){
            Resource.Error(ApiError(0, "Weak password, please make sure your password is strong"))
        }
        catch(e: FirebaseAuthInvalidCredentialsException){
            Resource.Error(ApiError(0, "Invalid email, please make sure your email is valid"))
        }
        catch(e : FirebaseAuthUserCollisionException){
            Resource.Error(ApiError(0, "This email already exists"))
        }
        catch (e: Exception) {
            Resource.Error(ApiError(0, "Unknown  error: ${e.message}"))
        }
    }

    override suspend fun login(email: String, password: String): Resource<Boolean> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(true)
        }
        catch(e : FirebaseAuthInvalidCredentialsException){
            Resource.Error(ApiError(0, "Make sure your email or password is valid"))
        }
        catch (e: Exception) {
            Resource.Error(ApiError(0, "Unknown  error: ${e.message}"))
        }
    }

    override suspend fun signOut(): Resource<Boolean> {
        return try {
            firebaseAuth.signOut()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(ApiError(0, "Unknown error: ${e.message}"))
        }
    }
}