package com.jamiescode.grazer.login.data.repository

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jamiescode.grazer.login.data.datasource.DataStoreManager
import com.jamiescode.grazer.login.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl
    @Inject
    constructor(
        dataStoreManager: DataStoreManager,
    ) : AuthRepository {
        private val dataStore = dataStoreManager.get()
        private val authTokenPrefKey = stringPreferencesKey("auth_token")

        override suspend fun getAuthToken(): Flow<String> =
            dataStore.data.map { preferences ->
                preferences[authTokenPrefKey] ?: ""
            }

        override suspend fun setAuthToken(authToken: String) {
            dataStore.edit { preferences ->
                preferences[authTokenPrefKey] = authToken
            }
        }
    }
