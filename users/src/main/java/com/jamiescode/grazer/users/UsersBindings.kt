package com.jamiescode.grazer.users

import com.jamiescode.grazer.users.data.repository.UserRepositoryImpl
import com.jamiescode.grazer.users.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UsersBindings {
    @Binds
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository
}
