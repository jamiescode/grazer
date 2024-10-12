package com.jamiescode.grazer.login

import com.jamiescode.grazer.login.data.repository.AuthRepositoryImpl
import com.jamiescode.grazer.login.data.repository.LoginRepositoryImpl
import com.jamiescode.grazer.login.domain.repository.AuthRepository
import com.jamiescode.grazer.login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginBindings {
    @Binds
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun provideLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}
