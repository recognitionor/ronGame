package com.jhlee.rongame.presentation.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.usecase.user.GetUserInfoUseCase
import com.jhlee.rongame.domain.usecase.user.InsertUserInfoUseCase
import com.jhlee.rongame.domain.usecase.user.UpdateUserMoneyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val insertUserInfoUseCase: InsertUserInfoUseCase,
    private val updateUserMoneyUseCase: UpdateUserMoneyUseCase
) : ViewModel() {
    private val _state = mutableStateOf(UserState())

    val state: State<UserState> = _state

    init {
        getUser()
    }

    fun updateUserInfoMoney(minusValue: Int) {
        updateUserMoneyUseCase(minusValue).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserState(isLoading = false, result.data)
                }

                is Resource.Loading -> {
                    _state.value = UserState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = UserState(isLoading = false, error = result.message ?: "error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getUser() {
        getUserInfoUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserState(isLoading = false, result.data)
                }

                is Resource.Loading -> {
                    _state.value = UserState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = UserState(error = result.message ?: "user info error")
                }
            }

        }.launchIn(viewModelScope)
        getUserInfoUseCase().launchIn(viewModelScope)
    }

    private fun insertUser(userInfo: UserInfo) {
        insertUserInfoUseCase(userInfo).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getUser()
                    _state.value = UserState(isLoading = false, result.data)
                }

                is Resource.Loading -> {
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