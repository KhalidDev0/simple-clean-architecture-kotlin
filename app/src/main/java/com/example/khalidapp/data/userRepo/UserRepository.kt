package com.example.khalidapp.data.userRepo

import com.example.khalidapp.common.utils.Resource
import com.example.khalidapp.model.User
import com.google.firebase.firestore.DocumentSnapshot

interface UserRepository {
    suspend fun addUser(uid: String, user: User) : Resource<Boolean>
    suspend fun getUser(uid: String) : Resource<DocumentSnapshot>
}