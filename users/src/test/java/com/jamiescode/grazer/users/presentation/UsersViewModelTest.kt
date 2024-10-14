package com.jamiescode.grazer.users.presentation

import com.jamiescode.grazer.users.domain.usecase.GetUsersUseCase
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UsersViewModelTest {
    private val getUsersUseCase: GetUsersUseCase = mockk(relaxed = true)
    val viewModel = UsersViewModel(getUsersUseCase)

    @Test
    fun `GIVEN X, WHEN Y, THEN Z`() {
        assertEquals(true, false)
    }
}
