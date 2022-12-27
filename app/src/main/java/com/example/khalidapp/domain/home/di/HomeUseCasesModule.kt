package com.example.khalidapp.domain.home.di

import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.domain.home.useCase.GetUserUseCase
import com.example.khalidapp.domain.home.useCase.HomeUseCases
import com.example.khalidapp.domain.home.useCase.SignOutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeUseCasesModule {

    @Singleton
    @Provides
    fun provideHomeUseCase(
        getUserUseCase: GetUserUseCase,
        signOutUseCase: SignOutUseCase
    ): HomeUseCases {
        return HomeUseCases(getUserUseCase,signOutUseCase)
    }
}