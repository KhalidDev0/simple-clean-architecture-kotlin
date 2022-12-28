package com.example.khalidapp.domain.auth.usecase

import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.presentation.common.utils.Resource

class AddUserUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(uid: String, user: User): Resource<Boolean> {
        return userRepository.addUser(uid, user)
    }
}