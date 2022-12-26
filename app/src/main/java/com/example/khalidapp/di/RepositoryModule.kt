package com.example.khalidapp.di

import com.example.khalidapp.data.authRepository.AuthRepository
import com.example.khalidapp.data.authRepository.AuthRepositoryImp
import com.example.khalidapp.data.userRepo.UserRepository
import com.example.khalidapp.data.userRepo.UserRepositoryImp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImp(firebaseAuth)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        firebaseFirestore: FirebaseFirestore
    ): UserRepository {
        return UserRepositoryImp(firebaseFirestore)
    }
}