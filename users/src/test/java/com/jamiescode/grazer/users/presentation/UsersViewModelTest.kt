package com.jamiescode.grazer.users.presentation

import androidx.lifecycle.Observer
import com.jamiescode.grazer.testutils.CoroutinesTestDispatcherExtension
import com.jamiescode.grazer.testutils.InstantTaskExecutorExtension
import com.jamiescode.grazer.users.domain.User
import com.jamiescode.grazer.users.domain.usecase.GetUsersUseCase
import com.jamiescode.grazer.users.presentation.UsersViewModel.State
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class UsersViewModelTest {
    private val getUsersUseCase: GetUsersUseCase = mockk(relaxed = true)
    private val viewModel = UsersViewModel(getUsersUseCase)
    private val users =
        listOf(
            User(
                name = "",
                dateOfBirthEpochSeconds = 0L,
                profileImageUrl = "",
            ),
        )

    @Test
    fun `GIVEN get users use case success, WHEN loadUsers, THEN post success`() =
        runTest {
            // GIVEN
            val observer = mockk<Observer<State>>(relaxed = true)
            viewModel.stateLiveData.observeForever(observer)

            val getUsersUseCaseResult = GetUsersUseCase.Result.Success(users)
            coEvery { getUsersUseCase.execute() } returns getUsersUseCaseResult

            // WHEN
            viewModel.loadUsers()

            // THEN
            verifyOrder {
                observer.onChanged(State.Loading)
                observer.onChanged(State.Loading)
                observer.onChanged(State.Loaded(users))
            }
        }

    @Test
    fun `GIVEN get users use case error, WHEN loadUsers, THEN post error`() =
        runTest {
            // GIVEN
            val observer = mockk<Observer<State>>(relaxed = true)
            viewModel.stateLiveData.observeForever(observer)

            val getUsersUseCaseResult = GetUsersUseCase.Result.Error(Exception())
            coEvery { getUsersUseCase.execute() } returns getUsersUseCaseResult

            // WHEN
            viewModel.loadUsers()

            // THEN
            verifyOrder {
                observer.onChanged(State.Loading)
                observer.onChanged(State.Loading)
                observer.onChanged(State.Error(message = "Credentials are incorrect"))
            }
        }
}
