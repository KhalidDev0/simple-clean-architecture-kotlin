package com.example.khalidapp.data.repository

import com.example.khalidapp.data.model.ApiError
import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.data.util.FirebaseConstants
import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.presentation.common.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepositoryImp(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : UserRepository {
    override suspend fun addUser(uid: String, user: User): Resource<Boolean> {
        return try {
            firebaseFirestore.collection(FirebaseConstants.USERS_NODE)
                .document(uid)
                .set(user)
                .await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(ApiError(0, "Cannot add user, error: ${e.message}"))
        }
    }

    override suspend fun getCurrentUser() : Resource<User> {
        return try {
            val result = firebaseFirestore.collection(FirebaseConstants.USERS_NODE)
                .document(firebaseAuth.uid!!)
                .get()
                .await()
            val user = User(
                result.data!!["email"] as String,
                result.data!!["name"] as String,
                result.data!!["age"] as String,
                result.data!!["gender"] as String
            )
            Resource.Success(user)
        }catch (e: Exception) {
            Resource.Error(ApiError(0, "Cannot get user, error: ${e.message}"))
        }
    }
}