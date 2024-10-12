package com.jamiescode.grazer

import com.jamiescode.grazer.data.repository.AuthRepositoryImpl
import com.jamiescode.grazer.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GrazerDependencies {
    @Binds
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
