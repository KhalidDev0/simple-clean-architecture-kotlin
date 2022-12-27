package com.example.khalidapp.domain.home.useCase

import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.presentation.common.utils.Resource

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Resource<User> {
        return when (val resource = userRepository.getCurrentUser()) {
            is Resource.Success ->  Resource.Success(resource.data)
            is Resource.Error ->  Resource.Error(resource.apiError)
            is Resource.Loading -> Resource.Loading()
        }
    }

}