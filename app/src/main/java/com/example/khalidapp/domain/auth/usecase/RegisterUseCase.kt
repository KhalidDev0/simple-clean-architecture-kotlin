package com.example.khalidapp.domain.auth.usecase

import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.presentation.common.utils.Resource
import com.google.firebase.auth.FirebaseUser

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email : String, password: String): Resource<FirebaseUser> {
        return authRepository.register(email,password)
    }
}