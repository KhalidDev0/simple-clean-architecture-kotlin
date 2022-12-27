package com.example.khalidapp.domain.auth.repository

import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.presentation.common.utils.Resource

interface UserRepository {
    suspend fun addUser(uid: String, user: User) : Resource<Boolean>
    suspend fun getCurrentUser() : Resource<User>
}