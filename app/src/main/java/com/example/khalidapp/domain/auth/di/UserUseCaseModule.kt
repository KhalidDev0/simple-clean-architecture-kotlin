package com.example.khalidapp.domain.auth.di

import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.domain.auth.usecase.AddUserUseCase
import com.example.khalidapp.domain.auth.usecase.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserUseCaseModule {

    @Singleton
    @Provides
    fun provideAddUserUseCase(
        userRepository: UserRepository
    ): AddUserUseCase {
        return AddUserUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserUseCase(
        userRepository: UserRepository
    ): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }
}