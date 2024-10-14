package com.jamiescode.grazer.users.domain.usecase

import com.jamiescode.grazer.users.domain.User
import com.jamiescode.grazer.users.domain.mapper.UserMapper
import com.jamiescode.grazer.users.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase
    @Inject
    constructor(
        private val userRepository: UserRepository,
        private val userMapper: UserMapper,
    ) {
        sealed class Result {
            data class Success(
                val users: List<User>,
            ) : Result()

            data class Error(
                val e: Throwable,
            ) : Result()
        }

        suspend fun execute(): Result {
            val result = userRepository.getUsers()

            val statusCode = result.status ?: 0
            val usersDto = result.data?.users ?: emptyList()
            val users = userMapper.execute(usersDto)

            return if (statusCode == STATUS_OK && users.isNotEmpty()) {
                Result.Success(users)
            } else {
                Result.Error(Exception("Get Users failed with status code: $statusCode"))
            }
        }

        companion object {
            const val STATUS_OK = 200
        }
    }
