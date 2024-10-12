package com.jamiescode.grazer.login.domain.usecase

import com.jamiescode.grazer.login.domain.repository.AuthRepository
import com.jamiescode.grazer.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase
    @Inject
    constructor(
        private val loginRepository: LoginRepository,
        private val authRepository: AuthRepository,
    ) {
        sealed class Result {
            data class Success(
                val authToken: String,
            ) : Result()

            data class Error(
                val e: Throwable,
            ) : Result()
        }

        suspend fun execute(
            email: String,
            password: String,
        ): Result {
            val result = loginRepository.login(email, password)

            val statusCode = result.status ?: 0
            val authToken = result.auth?.data?.authToken ?: ""

            if (statusCode == STATUS_OK && authToken.isNotBlank()) {
                authRepository.setAuthToken(authToken)
                return Result.Success(authToken)
            } else {
                return Result.Error(Exception("Login failed with status code: $statusCode"))
            }
        }

        companion object {
            private const val STATUS_OK = 200
        }
    }
