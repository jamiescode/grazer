package com.jamiescode.grazer.domain.repository

interface AuthRepository {
    suspend fun getAuthToken(): String

    suspend fun setAuthToken(authToken: String)
}
