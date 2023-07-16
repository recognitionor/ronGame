package com.jhlee.rongame.presentation.game

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.usecase.card.GetCardListUseCase
import com.jhlee.rongame.domain.usecase.game_stage.GetGameStageListUseCase
import com.jhlee.rongame.domain.usecase.game_stage.GetGameStageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getCardListUseCase: GetCardListUseCase,
    private val getGameStageUseCase: GetGameStageUseCase
) : ViewModel() {

    private val _state = mutableStateOf(GameState())

    val state: State<GameState> = _state

    fun setGameStageLoadFlag(isGameStageLoadDone: Boolean) {
        _state.value = _state.value.copy(isGameStageLoadDone = isGameStageLoadDone)
    }

    fun getGameStage(gameStageId: Int) {
        getGameStageUseCase(gameStageId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = _state.value.copy(
                            isLoading = false, isGameStageLoadDone = true, selectedGameStage = it
                        )
                    }
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getCardList() {
        getCardListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        _state.value = _state.value.copy(isLoading = false, cardList = it)
                    }
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(isLoading = true, error = _state.value.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}