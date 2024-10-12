package com.jamiescode.grazer.login.data.datasource.api

import com.jamiescode.grazer.login.data.datasource.api.model.LoginRequest
import com.jamiescode.grazer.login.data.datasource.api.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginRetrofitService {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse
}
