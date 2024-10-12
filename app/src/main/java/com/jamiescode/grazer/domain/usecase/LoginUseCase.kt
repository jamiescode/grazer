package com.jamiescode.grazer.domain.usecase

import javax.inject.Inject

class LoginUseCase
    @Inject
    constructor() {
        sealed class Result {
            data class Success(
                val authToken: String,
            ) : Result()

            data class Error(
                val e: Throwable,
            ) : Result()
        }

        suspend fun execute(
            username: String,
            password: String,
        ): Result {
            // Perform login request here
            return Result.Success("authToken:$username:$password")
        }
    }
