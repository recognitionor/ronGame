package com.jhlee.rongame.presentation.card

import com.jhlee.rongame.domain.model.Card

data class CardState(
    val isLoading: Boolean = false,
    val isLoadDone: Boolean = false,
    val card: Card? = null,
    val error: String = "",
    val progress: Int = 0
)