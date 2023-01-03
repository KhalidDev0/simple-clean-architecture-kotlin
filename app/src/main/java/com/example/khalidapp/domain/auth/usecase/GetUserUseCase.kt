package com.example.khalidapp.domain.auth.usecase

import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetUserUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(): Flow<Resource<User>> {
        return flow {
            emit(userRepository.getCurrentUser())
        }
    }

}