package com.jhlee.rongame.presentation.card_list

import com.jhlee.rongame.domain.model.Card

data class CardListState(
    val isLoading: Boolean = false,
    val isLoadDone: Boolean = false,
    val cardList: List<Card> = emptyList(),
    val error: String = "",
    val progress: Int = 0
)