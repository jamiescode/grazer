package com.jamiescode.grazer.users.data.repository

import com.jamiescode.grazer.login.domain.repository.AuthRepository
import com.jamiescode.grazer.users.UsersQualifier
import com.jamiescode.grazer.users.data.datasource.api.UsersRetrofitService
import com.jamiescode.grazer.users.data.datasource.api.model.UsersResponse
import com.jamiescode.grazer.users.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserRepositoryImpl
    @Inject
    constructor(
        private val authRepository: AuthRepository,
        @UsersQualifier private val usersRetrofitService: UsersRetrofitService,
    ) : UserRepository {
        override suspend fun getUsers(): UsersResponse {
            val authToken =
                runBlocking {
                    authRepository.getAuthToken().firstOrNull() ?: ""
                }
            return usersRetrofitService.login("Bearer $authToken")
        }
    }
