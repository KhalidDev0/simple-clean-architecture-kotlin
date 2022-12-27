package com.example.khalidapp.domain.home.di

import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.domain.home.useCase.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GetUserUseCaseModule {

    @Singleton
    @Provides
    fun provideGetUserUseCase(
        userRepository: UserRepository
    ): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }
}