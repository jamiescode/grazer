package com.jamiescode.grazer.users.domain

import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneOffset

data class User(
    val name: String,
    val dateOfBirthEpochSeconds: Long,
    val profileImageUrl: String,
    val diet: String = "Vegan",
    val relationshipStatus: String = "Single",
) {
    fun getAge(): Int {
        val localDate =
            Instant
                .ofEpochSecond(dateOfBirthEpochSeconds)
                .atZone(ZoneOffset.UTC)
                .toLocalDate()

        return Period
            .between(
                localDate,
                LocalDate.now(),
            ).years
    }
}
