package com.jhlee.rongame.presentation.card

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.common.constants.GradeConst
import com.jhlee.rongame.domain.usecase.card.CreateCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val createCardUseCard: CreateCardUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(CardState())

    val state: State<CardState> = _state

    fun gatchaCard() {
        createCardUseCard().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data != null) {
                        _state.value = CardState(isLoading = false, result.data)
                    } else {
                        _state.value = CardState(error = result.message ?: "card is null")
                    }

                }

                is Resource.Loading -> {
                    _state.value =
                        CardState(isLoading = true, progress = _state.value.progress.plus(1))
                }

                is Resource.Error -> {
                    _state.value = CardState(error = result.message ?: "user info error")
                }
            }

        }.launchIn(viewModelScope)
        createCardUseCard().launchIn(viewModelScope)
    }
}