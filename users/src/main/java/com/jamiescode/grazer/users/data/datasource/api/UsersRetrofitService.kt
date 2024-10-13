package com.jamiescode.grazer.users.data.datasource.api

import com.jamiescode.grazer.users.data.datasource.api.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface UsersRetrofitService {
    @Headers("Content-Type: application/json")
    @GET("users")
    suspend fun login(
        @Header("Authorization") authToken: String,
    ): UsersResponse
}
