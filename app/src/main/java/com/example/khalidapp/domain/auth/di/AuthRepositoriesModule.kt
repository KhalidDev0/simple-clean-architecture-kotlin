package com.example.khalidapp.domain.auth.di

import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.data.repository.AuthRepositoryImp
import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.data.repository.UserRepositoryImp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoriesModule {

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
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): UserRepository {
        return UserRepositoryImp(firebaseFirestore,firebaseAuth)
    }
}