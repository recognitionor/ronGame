package com.jhlee.rongame.presentation.card_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.usecase.card.GetCardListUseCase
import com.jhlee.rongame.domain.usecase.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(private val getCardListUseCase: GetCardListUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(CardListState())

    val state: State<CardListState> = _state

    init {
        getCardList()
    }

    fun getCardList() {

        getCardListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = CardListState(isLoading = false, cardList = it)
                    }
                }

                is Resource.Loading -> {
                    _state.value = CardListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = CardListState(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}