package com.example.khalidapp.domain.auth.usecase

import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.presentation.common.utils.Resource

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email : String, password: String): Resource<Boolean> {
        return authRepository.login(email,password)
    }
}