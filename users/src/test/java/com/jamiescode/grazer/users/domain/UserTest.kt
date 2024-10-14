package com.jamiescode.grazer.users.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class UserTest {
    private val user =
        User(
            name = "John Doe",
            dateOfBirthEpochSeconds = 946684800L, // 1st Jan 2000
            profileImageUrl = "https://example.com/profile.jpg",
            diet = "Vegan",
            relationshipStatus = "Single",
        )

    @Test
    fun `GIVEN now is 1st Nov 2024, WHEN getAge, THEN age is correct`() {
        // GIVEN
        val now = LocalDate.of(2024, 11, 1) // 1st Nov 2024

        // WHEN
        val actual = user.getAge(now = now)

        // THEN
        assertEquals(24, actual)
    }
}
