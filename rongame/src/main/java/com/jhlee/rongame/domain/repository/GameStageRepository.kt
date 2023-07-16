package com.jhlee.rongame.domain.repository

import com.jhlee.rongame.domain.model.GameStage

interface GameStageRepository {

    suspend fun insertGameStageList(list: List<GameStage>? = null): List<GameStage>

    suspend fun insertGameStage(gameStage: GameStage): Long
    suspend fun getGameStageList(): List<GameStage>
    suspend fun getGameStage(gameStageId: Int): GameStage
}