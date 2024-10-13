package com.jamiescode.grazer.users.domain.mapper

import com.jamiescode.grazer.users.data.datasource.api.model.UserDto
import com.jamiescode.grazer.users.domain.User
import javax.inject.Inject

class UserMapper
    @Inject
    constructor() {
        fun execute(userDto: List<UserDto>): List<User> =
            userDto.mapNotNull { dto ->
                val name = dto.name ?: ""
                val dateOfBirth = dto.dateOfBirth ?: 0L
                val profileImageUrl = dto.profileImageUrl ?: ""

                if (name.isNotBlank() && dateOfBirth != 0L && profileImageUrl.isNotBlank()) {
                    User(name, dateOfBirth, profileImageUrl)
                } else {
                    null
                }
            }
    }
