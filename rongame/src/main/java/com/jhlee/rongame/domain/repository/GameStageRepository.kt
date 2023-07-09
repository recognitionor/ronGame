package com.jhlee.rongame.domain.repository

import com.jhlee.rongame.domain.model.GameStage

interface GameStageRepository {
    suspend fun getGameList(): List<GameStage>
}