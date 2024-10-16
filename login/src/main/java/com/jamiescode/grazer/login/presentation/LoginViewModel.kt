package com.jamiescode.grazer.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamiescode.grazer.login.domain.usecase.LoginUseCase
import com.jamiescode.grazer.navigation.AppNavigator
import com.jamiescode.grazer.navigation.Destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val loginUseCase: LoginUseCase,
        private val appNavigator: AppNavigator,
    ) : ViewModel() {
        private val stateMutableLiveData: MutableLiveData<State> by lazy {
            MutableLiveData<State>(State.Idle)
        }
        val stateLiveData = stateMutableLiveData as LiveData<State>

        fun login(
            email: String,
            password: String,
        ) {
            stateMutableLiveData.postValue(State.Loading)
            viewModelScope.launch {
                loginUseCase.execute(email, password).also {
                    when (it) {
                        is LoginUseCase.Result.Success -> {
                            stateMutableLiveData.postValue(State.Idle)
                            appNavigator.navigateTo(Destinations.Users)
                        }

                        is LoginUseCase.Result.Error -> {
                            // Get the reason from the api and pass it on
                            stateMutableLiveData.postValue(State.Error("Credentials are incorrect"))
                        }
                    }
                }
            }
        }

        sealed class State {
            data object Idle : State()

            data object Loading : State()

            data class Error(
                val message: String,
            ) : State()
        }
    }
