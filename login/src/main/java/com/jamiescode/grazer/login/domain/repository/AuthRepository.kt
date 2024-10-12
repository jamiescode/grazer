package com.jamiescode.grazer.login.domain.repository

interface AuthRepository {
    suspend fun getAuthToken(): String

    suspend fun setAuthToken(authToken: String)
}
