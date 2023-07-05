package com.jhlee.rongame.presentation.user

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.usecase.user.GetUserInfoUseCase
import com.jhlee.rongame.domain.usecase.user.InsertUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val insertUserInfoUseCase: InsertUserInfoUseCase
) : ViewModel() {
    private val _state = mutableStateOf(UserState())

    val state: State<UserState> = _state

    init {
        getUser()
    }

    private fun getUser() {
        getUserInfoUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("jhlee", "Success")
                    _state.value = UserState(isLoading = false, result.data)
                }

                is Resource.Loading -> {
                    _state.value = UserState(isLoading = true)
                }

                is Resource.Error -> {
                    insertUser()
                    _state.value = UserState(error = result.message ?: "user info error")
                }
            }

        }.launchIn(viewModelScope)
        getUserInfoUseCase().launchIn(viewModelScope)
    }

    private fun insertUser() {
        insertUserInfoUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("jhlee", "insertUser-Success")
                    getUser()
                    _state.value = UserState(isLoading = false, result.data)
                }

                is Resource.Loading -> {
                    Log.d("jhlee", "insertUser-Loading")
                    _state.value = UserState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = UserState(error = result.message ?: "user info error")
                }
            }
        }.launchIn(viewModelScope)
        getUserInfoUseCase().launchIn(viewModelScope)
    }
}