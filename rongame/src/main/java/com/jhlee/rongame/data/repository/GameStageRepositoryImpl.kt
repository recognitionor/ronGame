package com.jhlee.rongame.data.repository

import com.jhlee.rongame.domain.const.GameStageConst.Companion.GAME_STAGE_STATUS_READY
import com.jhlee.rongame.domain.const.GameStageConst.Companion.GAME_STAGE_TYPE_CARD
import com.jhlee.rongame.domain.model.GameStage
import com.jhlee.rongame.domain.repository.GameStageRepository

class GameStageRepositoryImpl : GameStageRepository {

    override suspend fun getGameList(): List<GameStage> {
        return arrayListOf<GameStage>().apply {
            for (i in 0..100) {
                add(
                    GameStage(
                        i,
                        100 + i,
                        150 + i,
                        GAME_STAGE_STATUS_READY,
                        GAME_STAGE_TYPE_CARD,
                        i,
                        i,
                        i,
                        i,
                        i
                    )
                )
            }
        }
    }
}