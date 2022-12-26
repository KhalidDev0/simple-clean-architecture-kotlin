package com.example.khalidapp.data.userRepo

import com.example.khalidapp.common.utils.Resource
import com.example.khalidapp.model.User

interface UserRepository {
    suspend fun addUser(uid: String, user: User) : Resource<Boolean>
}