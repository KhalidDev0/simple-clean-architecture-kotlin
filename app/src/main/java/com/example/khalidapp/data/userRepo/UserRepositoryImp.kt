package com.example.khalidapp.data.userRepo

import com.example.khalidapp.common.utils.FirebaseConstants
import com.example.khalidapp.common.utils.Resource
import com.example.khalidapp.data.model.ApiError
import com.example.khalidapp.model.User
import com.google.api.ResourceProto.resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepositoryImp(
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {
    override suspend fun addUser(uid: String, user: User): Resource<Boolean> {
        return try {
            firebaseFirestore.collection(FirebaseConstants.USERS_NODE)
                .document(uid)
                .set(user)
                .await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(ApiError(0, "Cannot add user"))
        }
    }
}