package com.jamiescode.grazer.users.data.datasource.api.model

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("status") val status: Int?,
    @SerializedName("status_desc") val statusDescription: String?,
    @SerializedName("data") val data: UsersData?,
    @SerializedName("meta") val meta: UsersMeta?,
)

data class UsersData(
    @SerializedName("users") val users: List<UserDto>?,
)

data class UserDto(
    @SerializedName("name") val name: String?,
    @SerializedName("date_of_birth") val dateOfBirth: Long?,
    @SerializedName("profile_image") val profileImageUrl: String?,
)

data class UsersMeta(
    @SerializedName("item_count") val itemCount: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("current_page") val currentPage: Int?,
)
