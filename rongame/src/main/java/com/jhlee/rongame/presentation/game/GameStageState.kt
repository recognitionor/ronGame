package com.jhlee.rongame.presentation.game

import com.jhlee.rongame.domain.model.GameStage

data class GameStageState(
    val isLoading: Boolean = false,
    val isLoadDone: Boolean = false,
    val gameList: List<GameStage> = emptyList(),
    val error: String = "",
    val progress: Int = 0
)