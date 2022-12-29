package com.example.khalidapp.domain.auth.usecase

import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email : String, password: String): Flow<Resource<Boolean>> {
        return flow{
            emit(Resource.Loading())
            emit(authRepository.login(email, password))
        }
    }
}