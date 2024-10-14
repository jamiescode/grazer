package com.jamiescode.grazer.login.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jamiescode.grazer.login.data.datasource.DataStoreManager
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class AuthRepositoryImplTest {
    private val dataStoreManager: DataStoreManager = mockk(relaxed = true)
    private val dataStore: DataStore<Preferences> = mockk(relaxed = true)
    private val dataStoreFlow: Flow<Preferences> = mockk(relaxed = true)
    private val repository = AuthRepositoryImpl(dataStoreManager)

    @Test
    fun `WHEN getAuthToken, THEN call data store`() {
        // GIVEN auth token in data store
        coEvery { dataStoreManager.get() } returns dataStore
        coEvery { dataStore.data } returns dataStoreFlow
        // I need to work out how to mock the data store flow
        // coEvery { dataStoreFlow[AuthRepositoryImpl.AUTH_TOKEN_KEY] } returns ?

        // WHEN getAuthToken
        runBlocking { repository.getAuthToken() }

        // THEN data store called
        coVerify { dataStoreManager.get() }
    }

    @Test
    fun `WHEN setAuthToken, THEN call data store`() {
        // GIVEN auth token in data store
        coEvery { dataStoreManager.get() } returns dataStore
        // I need to work out how to mock the data store pair
        // coEvery { dataStore.edit { any() } } returns ?

        // WHEN setAuthToken
        val authToken = "authToken"
        runBlocking { repository.setAuthToken(authToken) }

        // THEN data store called
        coVerify { dataStoreManager.get() }
    }
}
