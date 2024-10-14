package com.jamiescode.grazer.users.data.repository

import com.jamiescode.grazer.login.domain.repository.AuthRepository
import com.jamiescode.grazer.users.data.datasource.api.UsersRetrofitService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class UserRepositoryImplTest {
    private val authRepository: AuthRepository = mockk(relaxed = true)
    private val retrofitService: UsersRetrofitService = mockk(relaxed = true)
    private val repository = UserRepositoryImpl(authRepository, retrofitService)

    @Test
    fun `GIVEN auth token present, WHEN getUsers, THEN make request with auth token`() {
        // GIVEN
        val authToken = "authToken"
        val authTokenFlow = flowOf(authToken)
        coEvery { authRepository.getAuthToken() } returns authTokenFlow

        // WHEN
        runBlocking { repository.getUsers() }

        // THEN
        coEvery { authRepository.getAuthToken() }
        coEvery { retrofitService.login("Bearer: $authToken") }
    }
}
