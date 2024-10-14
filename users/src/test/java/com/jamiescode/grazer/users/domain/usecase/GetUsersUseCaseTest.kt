package com.jamiescode.grazer.users.domain.usecase

import com.jamiescode.grazer.users.data.datasource.api.model.UserDto
import com.jamiescode.grazer.users.data.datasource.api.model.UsersData
import com.jamiescode.grazer.users.data.datasource.api.model.UsersResponse
import com.jamiescode.grazer.users.domain.User
import com.jamiescode.grazer.users.domain.mapper.UserMapper
import com.jamiescode.grazer.users.domain.repository.UserRepository
import com.jamiescode.grazer.users.domain.usecase.GetUsersUseCase.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetUsersUseCaseTest {
    private val userRepository: UserRepository = mockk(relaxed = true)
    private val userMapper: UserMapper = mockk(relaxed = true)
    private val useCase = GetUsersUseCase(userRepository, userMapper)

    private val userDtoList =
        listOf(
            UserDto(
                name = "name",
                dateOfBirth = 0L,
                profileImageUrl = "profileImageUrl",
            ),
        )
    private val users =
        listOf(
            User(
                name = "name",
                dateOfBirthEpochSeconds = 0L,
                profileImageUrl = "profileImageUrl",
            ),
        )

    @Test
    fun `GIVEN success response, WHEN executing, THEN success state`() {
        // GIVEN
        val usersResponse =
            UsersResponse(
                status = 200,
                data =
                    UsersData(
                        users = userDtoList,
                    ),
                statusDescription = "OK",
                meta = null,
            )
        coEvery { userRepository.getUsers() } returns usersResponse
        coEvery { userMapper.execute(userDtoList) } returns users

        // WHEN
        val result = runBlocking { useCase.execute() }

        // THEN
        assertEquals(Result.Success(users), result)
    }

    @Test
    fun `GIVEN error response, WHEN executing, THEN error state`() {
        // GIVEN
        val usersResponse =
            UsersResponse(
                status = 404,
                data =
                    UsersData(
                        users = userDtoList,
                    ),
                statusDescription = "NOT FOUND",
                meta = null,
            )
        coEvery { userRepository.getUsers() } returns usersResponse
        coEvery { userMapper.execute(userDtoList) } returns users

        // WHEN
        val result = runBlocking { useCase.execute() }

        // THEN
        result.shouldBeInstanceOf<Result.Error>()
        val resultErrorMessage = (result as? Result.Error)?.e?.message
        assertEquals("Get Users failed with status code: 404", resultErrorMessage)
    }
}
