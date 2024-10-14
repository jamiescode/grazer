package com.jamiescode.grazer.login.data.repository

import com.jamiescode.grazer.login.data.datasource.api.LoginRetrofitService
import com.jamiescode.grazer.login.data.datasource.api.model.LoginAuth
import com.jamiescode.grazer.login.data.datasource.api.model.LoginData
import com.jamiescode.grazer.login.data.datasource.api.model.LoginResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoginRepositoryImplTest {
    private val retrofitService: LoginRetrofitService = mockk(relaxed = true)
    private val repository = LoginRepositoryImpl(retrofitService)

    @Test
    fun `GIVEN successful response, WHEN login, THEN return api response`() {
        // GIVEN
        val loginResponse =
            LoginResponse(
                status = 200,
                statusDescription = "OK",
                auth =
                    LoginAuth(
                        data =
                            LoginData(
                                authToken = "authToken",
                            ),
                    ),
            )
        coEvery { retrofitService.login(any()) } returns loginResponse

        // WHEN login
        val result = runBlocking { repository.login("email", "password") }

        // THEN return data correctly
        assertEquals(loginResponse, result)
    }

    @Test
    fun `GIVEN error response, WHEN login, THEN return api response`() {
        // GIVEN
        val loginResponse =
            LoginResponse(
                status = 404,
                statusDescription = "NOT FOUND",
                auth = null,
            )
        coEvery { retrofitService.login(any()) } returns loginResponse

        // WHEN login
        val result = runBlocking { repository.login("email", "password") }

        // THEN return data correctly
        assertEquals(loginResponse, result)
    }
}
