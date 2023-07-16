package com.jhlee.rongame.presentation.game

import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.GameStage

data class GameState(
    val isLoading: Boolean = false,
    val isGameStageLoadDone: Boolean = false,
    val cardList: List<Card> = emptyList(),
    val usedCardCount: Int = 0,
    val selectedGameStage: GameStage? = null,
    val selectedCardList: List<Card?> = emptyList(),
    val error: String = "",
    val progress: Int = 0
)