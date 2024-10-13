package com.jamiescode.grazer.users.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamiescode.grazer.users.domain.User
import com.jamiescode.grazer.users.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel
    @Inject
    constructor(
        private val getUsersUseCase: GetUsersUseCase,
    ) : ViewModel() {
        private val stateMutableLiveData: MutableLiveData<State> by lazy {
            MutableLiveData<State>(State.Loading)
        }
        val stateLiveData = stateMutableLiveData as LiveData<State>

        fun loadUsers() {
            stateMutableLiveData.postValue(State.Loading)
            viewModelScope.launch {
                getUsersUseCase.execute().also {
                    when (it) {
                        is GetUsersUseCase.Result.Success -> {
                            stateMutableLiveData.postValue(State.Loaded(it.users))
                        }

                        is GetUsersUseCase.Result.Error -> {
                            // Get the reason from the api and pass it on
                            stateMutableLiveData.postValue(State.Error("Credentials are incorrect"))
                        }
                    }
                }
            }
        }

        sealed class State {
            data object Loading : State()

            data class Loaded(
                val users: List<User>,
            ) : State()

            data class Error(
                val message: String,
            ) : State()
        }
    }
