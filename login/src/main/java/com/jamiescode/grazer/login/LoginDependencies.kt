package com.jamiescode.grazer.login

import com.jamiescode.grazer.login.data.datasource.api.LoginRetrofitService
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
object LoginDependencies {
    @Singleton
    @Provides
    @LoginQualifier
    fun provideRetrofit(
        @LoginQualifier okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://grazer.nw.r.appspot.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @LoginQualifier
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY),
            ).build()

    @Singleton
    @Provides
    @LoginQualifier
    fun provideRetrofitService(
        @LoginQualifier retrofit: Retrofit,
    ): LoginRetrofitService =
        retrofit.create(
            LoginRetrofitService::class.java,
        )
}
