package com.jamiescode.grazer.presentation.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamiescode.grazer.domain.repository.AuthRepository
import com.jamiescode.grazer.domain.usecase.LoginUseCase
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
        private val authRepository: AuthRepository,
        private val appNavigator: AppNavigator,
    ) : ViewModel() {
        private val stateMutableLiveData: MutableLiveData<State> by lazy {
            MutableLiveData<State>(State.Initial)
        }
        val stateLiveData = stateMutableLiveData as LiveData<State>

        fun login(
            username: String,
            password: String,
        ) {
            viewModelScope.launch {
                loginUseCase.execute(username, password).also {
                    when (it) {
                        is LoginUseCase.Result.Success -> {
                            authRepository.setAuthToken(it.authToken)
                            appNavigator.navigateTo(Destinations.UserList)
                        }

                        is LoginUseCase.Result.Error -> {
                            stateMutableLiveData.postValue(State.Error)
                        }
                    }
                }
            }
        }

        sealed class State {
            data object Initial : State()

            data object Error : State()
        }
    }
