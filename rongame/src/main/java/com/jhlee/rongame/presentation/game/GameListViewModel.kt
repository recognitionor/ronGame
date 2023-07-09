package com.jhlee.rongame.presentation.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.usecase.game_stage.GetGameStageListUseCase
import com.jhlee.rongame.presentation.card.CardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(private val getGameStageListUseCase: GetGameStageListUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(GameStageState())

    val state: State<GameStageState> = _state

    init {
        getGameList()
    }

    private fun getGameList() {
        getGameStageListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = GameStageState(isLoading = false, gameList = it)
                    }
                }

                is Resource.Error -> {
                    _state.value = GameStageState(isLoading = false)
                }

                is Resource.Loading -> {
                    _state.value = GameStageState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}