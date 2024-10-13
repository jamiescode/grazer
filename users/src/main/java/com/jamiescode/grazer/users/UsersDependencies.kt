package com.jamiescode.grazer.users

import com.jamiescode.grazer.users.data.datasource.api.UsersRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UsersDependencies {
    @Singleton
    @Provides
    @UsersQualifier
    fun provideRetrofit(
        @UsersQualifier okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://grazer.nw.r.appspot.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @UsersQualifier
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY),
            ).build()

    @Singleton
    @Provides
    @UsersQualifier
    fun provideRetrofitService(
        @UsersQualifier retrofit: Retrofit,
    ): UsersRetrofitService =
        retrofit.create(
            UsersRetrofitService::class.java,
        )
}
