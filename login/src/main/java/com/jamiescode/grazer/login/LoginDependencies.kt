package com.jamiescode.grazer.login

import com.jamiescode.grazer.login.data.repository.AuthRepositoryImpl
import com.jamiescode.grazer.login.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginDependencies {
    @Binds
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
