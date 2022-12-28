package com.example.khalidapp.domain.auth.usecase

import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.presentation.common.utils.Resource

class SignOutUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Resource<Boolean>{
        return authRepository.signOut()
    }

}