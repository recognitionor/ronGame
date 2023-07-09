package com.jhlee.rongame.presentation.splash

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.data.local.InitData
import com.jhlee.rongame.data.local.entity.toHero
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.usecase.hero.CreateHeroUseCase
import com.jhlee.rongame.domain.usecase.hero.GetHeroUseCase
import com.jhlee.rongame.domain.usecase.user.GetUserInfoUseCase
import com.jhlee.rongame.domain.usecase.user.InsertUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getHeroUseCase: GetHeroUseCase,
    private val createHeroUseCase: CreateHeroUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val insertUserInfoUseCase: InsertUserInfoUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SplashState())

    val state: State<SplashState> = _state

    init {
        getHeroList()
        getUserInfo()
    }

    fun insertUserInfo(userInfo: UserInfo) {
        insertUserInfoUseCase(userInfo).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        SplashState(false, userInfo = result.data, heroList = _state.value.heroList)
                }

                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserInfo() {
        getUserInfoUseCase().onEach { reulst ->
            when (reulst) {
                is Resource.Success -> {
                    Log.d(
                        "jhlee",
                        "getUserInfo_Success ${_state.value.heroList}-${_state.value.userInfo}"
                    )
                    _state.value =
                        SplashState(false, userInfo = reulst.data, heroList = _state.value.heroList)
                    Log.d(
                        "jhlee",
                        "getUserInfo_Success ${_state.value.heroList}-${_state.value.userInfo}"
                    )
                }

                is Resource.Error -> {
                    SplashState(false)
                }

                is Resource.Loading -> {
                    _state.value = SplashState(true)
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun createHeroList() {
        val data = InitData.heroList.map {
            it.toHero()
        }
        createHeroUseCase(data).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = SplashState(true)
                }

                is Resource.Success -> {
                    Log.d(
                        "jhlee",
                        "createHeroList_Success ${_state.value.heroList}-${_state.value.userInfo}"
                    )
                    result.data?.let {
                        _state.value = SplashState(
                            false, userInfo = _state.value.userInfo, heroList = result.data
                        )
                    }
                    Log.d(
                        "jhlee",
                        "createHeroList_Success ${_state.value.heroList}-${_state.value.userInfo}"
                    )
                }

                is Resource.Error -> {
                    _state.value = SplashState(false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getHeroList() {
        getHeroUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d(
                        "jhlee",
                        "getHeroList_Success ${_state.value.heroList}-${_state.value.userInfo}"
                    )
                    result.data?.let { list ->
                        if (list.isEmpty()) {
                            createHeroList()
                        } else {
                            _state.value = SplashState(
                                isLoading = false,
                                heroList = list,
                                userInfo = _state.value.userInfo
                            )
                        }
                    }
                    Log.d(
                        "jhlee",
                        "getHeroList_Success ${_state.value.heroList}-${_state.value.userInfo}"
                    )
                }

                is Resource.Error -> {
                    _state.value = SplashState(isLoading = false)
                }

                is Resource.Loading -> {
                    _state.value = SplashState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}