package com.jamiescode.grazer.users.domain.repository

import com.jamiescode.grazer.users.data.datasource.api.model.UsersResponse

interface UserRepository {
    suspend fun getUsers(): UsersResponse
}
