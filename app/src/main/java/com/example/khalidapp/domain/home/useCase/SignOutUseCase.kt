package com.example.khalidapp.domain.home.useCase

import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.presentation.common.utils.Resource

class SignOutUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Resource<String>{
        return when(val resource = authRepository.signOut()){
            is Resource.Success -> Resource.Success("Signed out successfully")
            is Resource.Error -> Resource.Error(resource.apiError)
            is Resource.Loading -> Resource.Loading()
        }
    }

}