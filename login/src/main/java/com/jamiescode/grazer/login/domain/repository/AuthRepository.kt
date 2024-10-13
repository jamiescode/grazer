package com.jamiescode.grazer.login.domain.repository

import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface AuthRepository {
    suspend fun getAuthToken(): Flow<String>

    suspend fun setAuthToken(authToken: String)
}
