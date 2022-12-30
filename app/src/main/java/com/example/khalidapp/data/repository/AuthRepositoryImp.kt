package com.example.khalidapp.data.repository

import com.example.khalidapp.data.model.ApiError
import com.example.khalidapp.data.util.FirebaseAuthExceptions
import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.presentation.common.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
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
        } catch (e: FirebaseAuthException) {
            return when (e.errorCode) {
                FirebaseAuthExceptions.ERROR_EMAIL_ALREADY_IN_USE -> Resource.Error(
                    ApiError(
                        0,
                        "The email address is already in use"
                    )
                )
                else -> Resource.Error(ApiError(0, e.errorCode))
            }

        } catch (e: Exception) {
            Resource.Error(ApiError(0, "Something went wrong!"))
        }
    }

    override suspend fun login(email: String, password: String): Resource<Boolean> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(true)
        } catch (e: FirebaseAuthException) {

            return when (e.errorCode) {
                FirebaseAuthExceptions.ERROR_USER_NOT_FOUND -> Resource.Error(
                    ApiError(
                        0,
                        "Make sure that your email and password is correct"
                    )
                )
                else -> Resource.Error(ApiError(0, e.errorCode))
            }

        } catch (e: Exception) {
            Resource.Error(ApiError(0, "Something went wrong!"))
        }
    }

    override suspend fun signOut(): Resource<Boolean> {
        return try {
            firebaseAuth.signOut()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(ApiError(0, "Something went wrong!"))
        }
    }
}