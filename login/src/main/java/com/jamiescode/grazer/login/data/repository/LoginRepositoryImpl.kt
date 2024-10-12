package com.jamiescode.grazer.login.data.repository

import com.jamiescode.grazer.login.LoginQualifier
import com.jamiescode.grazer.login.data.datasource.api.LoginRetrofitService
import com.jamiescode.grazer.login.data.datasource.api.model.LoginRequest
import com.jamiescode.grazer.login.data.datasource.api.model.LoginResponse
import com.jamiescode.grazer.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl
    @Inject
    constructor(
        @LoginQualifier private val service: LoginRetrofitService,
    ) : LoginRepository {
        override suspend fun login(
            email: String,
            password: String,
        ): LoginResponse {
            val loginRequest = LoginRequest(email, password)
            return service.login(loginRequest)
        }
    }
