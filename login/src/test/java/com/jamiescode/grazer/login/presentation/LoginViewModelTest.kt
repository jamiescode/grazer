package com.jamiescode.grazer.login.presentation

import com.jamiescode.grazer.login.domain.usecase.LoginUseCase
import com.jamiescode.grazer.navigation.AppNavigator
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoginViewModelTest {
    private val loginUseCase: LoginUseCase = mockk(relaxed = true)
    private val appNavigator: AppNavigator = mockk(relaxed = true)
    val viewModel = LoginViewModel(loginUseCase, appNavigator)

    @Test
    fun `GIVEN X, WHEN Y, THEN Z`() {
        assertEquals(true, false)
    }
}
