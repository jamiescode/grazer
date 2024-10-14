package com.jamiescode.grazer.users.domain.mapper

import com.jamiescode.grazer.users.data.datasource.api.model.UserDto
import com.jamiescode.grazer.users.domain.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserMapperTest {
    private val mapper = UserMapper()

    @Test
    fun `GIVEN valid dto, WHEN mapping, THEN return correct result`() {
        // GIVEN
        val userDto =
            UserDto(
                name = "name",
                dateOfBirth = 0L,
                profileImageUrl = "profileImageUrl",
            )

        // WHEN
        val result = mapper.execute(listOf(userDto))

        // THEN
        val user =
            User(
                name = "name",
                dateOfBirthEpochSeconds = 0L,
                profileImageUrl = "profileImageUrl",
            )
        assertEquals(listOf(user), result)
    }

    @Test
    fun `GIVEN null dto, WHEN mapping, THEN return correct result`() {
        // GIVEN
        val userDto =
            UserDto(
                name = null,
                dateOfBirth = null,
                profileImageUrl = null,
            )

        // WHEN
        val result = mapper.execute(listOf(userDto))

        // THEN
        assertEquals(emptyList<User>(), result)
    }
}
