package com.jamiescode.grazer.login.domain.usercase

import com.jamiescode.grazer.login.data.datasource.api.model.LoginAuth
import com.jamiescode.grazer.login.data.datasource.api.model.LoginData
import com.jamiescode.grazer.login.data.datasource.api.model.LoginResponse
import com.jamiescode.grazer.login.domain.repository.AuthRepository
import com.jamiescode.grazer.login.domain.repository.LoginRepository
import com.jamiescode.grazer.login.domain.usecase.LoginUseCase
import com.jamiescode.grazer.login.domain.usecase.LoginUseCase.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoginUseCaseTest {
    private val loginRepository: LoginRepository = mockk(relaxed = true)
    private val authRepository: AuthRepository = mockk(relaxed = true)
    private val loginUseCase = LoginUseCase(loginRepository, authRepository)
    private val email = "email"
    private val password = "password"
    private val authToken = "authToken"

    @Test
    fun `GIVEN login response success, WHEN execute, THEN set auth token`() {
        val loginResponse =
            LoginResponse(
                status = 200,
                statusDescription = "OK",
                auth =
                    LoginAuth(
                        data =
                            LoginData(
                                authToken = authToken,
                            ),
                    ),
            )

        // GIVEN
        coEvery { loginRepository.login(email, password) } returns loginResponse
        coEvery { authRepository.setAuthToken(authToken) } returns Unit

        // WHEN
        val result = runBlocking { loginUseCase.execute(email, password) }

        // THEN
        coVerify { loginRepository.login(email, password) }
        coVerify { authRepository.setAuthToken(authToken) }

        assertEquals(Result.Success(authToken), result)
    }

    @Test
    fun `GIVEN login response error, WHEN execute, THEN set auth token`() {
        val loginResponse =
            LoginResponse(
                status = 404,
                statusDescription = "NOT FOUND",
                auth = null,
            )

        // GIVEN
        coEvery { loginRepository.login(email, password) } returns loginResponse
        coEvery { authRepository.setAuthToken(authToken) } returns Unit

        // WHEN
        val result = runBlocking { loginUseCase.execute(email, password) }

        // THEN
        coVerify { loginRepository.login(email, password) }

        result.shouldBeInstanceOf<Result.Error>()
        val resultErrorMessage = (result as? Result.Error)?.e?.message ?: ""
        assertEquals("Login failed with status code: 404", resultErrorMessage)
    }
}
