package com.example.khalidapp.domain.auth.di

import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.domain.auth.usecase.LoginUseCase
import com.example.khalidapp.domain.auth.usecase.RegisterUseCase
import com.example.khalidapp.domain.auth.usecase.SignOutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideRegisterUseCase(
        authRepository: AuthRepository
    ): RegisterUseCase {
        return RegisterUseCase(authRepository)
    }

    @Singleton
    @Provides
    fun provideSignOutUseCase(
        authRepository: AuthRepository
    ): SignOutUseCase {
        return SignOutUseCase(authRepository)
    }
}