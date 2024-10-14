package com.jamiescode.grazer.users.presentation

import com.jamiescode.grazer.users.domain.User
import com.jamiescode.grazer.users.domain.usecase.GetUsersUseCase
import com.jamiescode.grazer.users.presentation.UsersViewModel.State
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
    fun `GIVEN get users use case success, WHEN loadUsers, THEN post success`() {
        // GIVEN
        val getUsersUseCaseResult = GetUsersUseCase.Result.Success(users)
        coEvery { getUsersUseCase.execute() } returns getUsersUseCaseResult

        // WHEN
        viewModel.loadUsers()

        // THEN
        val state = viewModel.stateLiveData.value
        assertEquals(State.Loaded(users), state)
    }

    @Test
    fun `GIVEN get users use case error, WHEN loadUsers, THEN post error`() {
        // GIVEN
        val getUsersUseCaseResult = GetUsersUseCase.Result.Error(Exception())
        coEvery { getUsersUseCase.execute() } returns getUsersUseCaseResult

        // WHEN
        viewModel.loadUsers()

        // THEN
        val state = viewModel.stateLiveData.value
        assertEquals(State.Error(message = "Credentials are incorrect"), state)
    }
}
