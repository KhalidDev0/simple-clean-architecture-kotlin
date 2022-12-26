package com.example.khalidapp.data.userRepo

import com.example.khalidapp.common.utils.FirebaseConstants
import com.example.khalidapp.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepositoryImp(
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {
    override suspend fun addUser(uid: String, user: User): Boolean {
        return try {
            firebaseFirestore.collection(FirebaseConstants.USERS_NODE)
                .document(uid)
                .set(user)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }
}