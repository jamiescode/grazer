package com.jamiescode.grazer.login.presentation

import androidx.lifecycle.Observer
import com.jamiescode.grazer.login.domain.usecase.LoginUseCase
import com.jamiescode.grazer.login.presentation.LoginViewModel.State
import com.jamiescode.grazer.navigation.AppNavigator
import com.jamiescode.grazer.navigation.Destinations
import com.jamiescode.grazer.testutils.CoroutinesTestDispatcherExtension
import com.jamiescode.grazer.testutils.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class LoginViewModelTest {
    private val loginUseCase: LoginUseCase = mockk(relaxed = true)
    private val appNavigator: AppNavigator = mockk(relaxed = true)
    private val viewModel = LoginViewModel(loginUseCase, appNavigator)
    private val email = "email"
    private val password = "password"

    @Test
    fun `GIVEN login use case success, WHEN login, THEN post success and navigate to users`() =
        runTest {
            // GIVEN
            val observer = mockk<Observer<State>>(relaxed = true)
            viewModel.stateLiveData.observeForever(observer)

            val loginUseCaseResult = LoginUseCase.Result.Success("authToken")
            coEvery { loginUseCase.execute(email, password) } returns loginUseCaseResult

            // WHEN
            viewModel.login(email, password)

            // THEN
            verifyOrder {
                observer.onChanged(State.Idle)
                observer.onChanged(State.Loading)
                observer.onChanged(State.Idle)
            }

            coVerify { loginUseCase.execute(email, password) }
            coVerify { appNavigator.navigateTo(Destinations.Users) }
        }

    @Test
    fun `GIVEN login use case error, WHEN login, THEN post error`() =
        runTest {
            // GIVEN
            val observer = mockk<Observer<State>>(relaxed = true)
            viewModel.stateLiveData.observeForever(observer)

            val loginUseCaseResult = LoginUseCase.Result.Error(e = Exception("Login failed"))
            coEvery { loginUseCase.execute(email, password) } returns loginUseCaseResult

            // WHEN
            runBlocking { viewModel.login(email, password) }

            // THEN
            verifyOrder {
                observer.onChanged(State.Idle)
                observer.onChanged(State.Loading)
                observer.onChanged(State.Error("Credentials are incorrect"))
            }

            coVerify { loginUseCase.execute(email, password) }
        }
}
