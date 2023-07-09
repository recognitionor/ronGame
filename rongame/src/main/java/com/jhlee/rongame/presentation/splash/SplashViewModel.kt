package com.jhlee.rongame.presentation.splash

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.usecase.user.GetUserInfoUseCase
import com.jhlee.rongame.domain.usecase.user.InsertUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val insertUserInfoUseCase: InsertUserInfoUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SplashState())

    val state: State<SplashState> = _state

    init {
        getUserInfo()
    }

    fun insertUserInfo(userInfo: UserInfo) {
        insertUserInfoUseCase(userInfo).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("jhlee", "succss")
                    _state.value =
                        SplashState(false, userInfo = result.data)
                }

                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserInfo() {
        Log.d("jhlee", "getUserInfo")
        getUserInfoUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("jhlee", "getUserInfoSuccess")
                    _state.value = SplashState(false, userInfo = result.data)
                }

                is Resource.Error -> {
                    _state.value = SplashState(false)
                }

                is Resource.Loading -> {
                    _state.value = SplashState(true)
                }
            }

        }.launchIn(viewModelScope)
    }
}