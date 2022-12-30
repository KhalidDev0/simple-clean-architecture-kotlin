package com.example.khalidapp.domain.auth.usecase

import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.presentation.common.utils.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<FirebaseUser>> {
        return flow {
            emit(Resource.Loading())
            emit(authRepository.register(email, password))
        }
    }
}