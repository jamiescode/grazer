package com.jamiescode.grazer.login.data.datasource.api.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status: Int?,
    @SerializedName("status_desc") val statusDescription: String?,
    @SerializedName("auth") val auth: LoginAuth?,
)

data class LoginAuth(
    @SerializedName("data") val data: LoginData?,
)

data class LoginData(
    @SerializedName("token") val authToken: String?,
)
