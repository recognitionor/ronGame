package com.jhlee.rongame.presentation.splash

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.data.local.InitData
import com.jhlee.rongame.data.local.entity.toHero
import com.jhlee.rongame.domain.usecase.hero.CreateHeroUseCase
import com.jhlee.rongame.domain.usecase.hero.GetHeroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getHeroUseCase: GetHeroUseCase,
    private val createHeroUseCase: CreateHeroUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SplashState())

    val state: State<SplashState> = _state

    init {
        initializeDataLoad()
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
                    result.data?.let {
                        _state.value = SplashState(false, result.data)
                    }
                }

                is Resource.Error -> {
                    _state.value = SplashState(false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun initializeDataLoad() {
        getHeroUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("jhlee", "initializeDataLoad_Success : ${_state.value.result.size}")
                    result.data?.let { list ->
                        if (list.isEmpty()) {
                            createHeroList()
                        } else {
                            _state.value = SplashState(isLoading = false, result = list)
                        }
                    }
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