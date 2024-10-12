package com.jamiescode.grazer.data.repository

import com.jamiescode.grazer.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor() : AuthRepository {
        // Use Secure Shared Preferences instead of this
        private var authToken = ""

        override suspend fun getAuthToken(): String = authToken

        override suspend fun setAuthToken(authToken: String) {
            this.authToken = authToken
        }
    }
