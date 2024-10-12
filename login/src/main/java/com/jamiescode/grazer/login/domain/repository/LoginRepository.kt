package com.jamiescode.grazer.login.domain.repository

import com.jamiescode.grazer.login.data.datasource.api.model.LoginResponse

interface LoginRepository {
    suspend fun login(
        email: String,
        password: String,
    ): LoginResponse
}
