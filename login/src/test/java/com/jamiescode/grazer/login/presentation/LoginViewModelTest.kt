package com.jamiescode.grazer.login.presentation

import com.jamiescode.grazer.login.domain.usecase.LoginUseCase
import com.jamiescode.grazer.login.presentation.LoginViewModel.State
import com.jamiescode.grazer.navigation.AppNavigator
import com.jamiescode.grazer.navigation.Destinations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoginViewModelTest {
    private val loginUseCase: LoginUseCase = mockk(relaxed = true)
    private val appNavigator: AppNavigator = mockk(relaxed = true)
    private val viewModel = LoginViewModel(loginUseCase, appNavigator)
    private val email = "email"
    private val password = "password"

    @Test
    fun `GIVEN login use case success, WHEN login, THEN post success and navigate to users`() {
        // GIVEN
        val loginUseCaseResult = LoginUseCase.Result.Success("authToken")
        coEvery { loginUseCase.execute(email, password) } returns loginUseCaseResult

        // WHEN
        viewModel.login(email, password)

        // THEN
        val state = viewModel.stateLiveData.value
        assertEquals(State.Idle, state)

        coVerify { loginUseCase.execute(email, password) }
        coVerify { appNavigator.navigateTo(Destinations.Users) }
    }

    @Test
    fun `GIVEN login use case error, WHEN login, THEN post error`() {
        // GIVEN
        val loginUseCaseResult = LoginUseCase.Result.Error(e = Exception("Login failed"))
        coEvery { loginUseCase.execute(email, password) } returns loginUseCaseResult

        // WHEN
        viewModel.login(email, password)

        // THEN
        val state = viewModel.stateLiveData.value
        assertEquals(State.Error("Credentials are incorrect"), state)

        coVerify { loginUseCase.execute(email, password) }
    }
}
