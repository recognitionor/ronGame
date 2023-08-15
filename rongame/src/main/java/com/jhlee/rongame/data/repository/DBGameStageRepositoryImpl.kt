package com.jhlee.rongame.data.repository

import com.jhlee.rongame.data.local.dao.DBGameStageDao
import com.jhlee.rongame.data.local.entity.DBGameStage
import com.jhlee.rongame.data.local.entity.toGameDBStage
import com.jhlee.rongame.data.local.entity.toGameStage
import com.jhlee.rongame.domain.const.GameStageConst.Companion.GAME_STAGE_STATUS_NOT_OPEN
import com.jhlee.rongame.domain.const.GameStageConst.Companion.GAME_STAGE_STATUS_READY
import com.jhlee.rongame.domain.const.GameStageConst.Companion.GAME_STAGE_TYPE_CARD
import com.jhlee.rongame.domain.model.GameStage
import com.jhlee.rongame.domain.repository.GameStageRepository
import javax.inject.Inject


class DBGameStageRepositoryImpl @Inject constructor(private val gameStageDao: DBGameStageDao) :
    GameStageRepository {
    override suspend fun insertGameStageList(list: List<GameStage>?): List<GameStage> {
        if (list == null) {
            val tempList = mutableListOf<DBGameStage>()
            for (i in 1..100) {
                tempList.add(
                    DBGameStage(
                        id = i,
                        cost = i * 10,
                        reward = i * 20,
                        status = if (i == 1) GAME_STAGE_STATUS_READY else GAME_STAGE_STATUS_NOT_OPEN,
                        image = "ic_done",
                        type = GAME_STAGE_TYPE_CARD
                    )
                )
            }

            gameStageDao.createList(tempList)
        } else {
            gameStageDao.createList(list.map {
                it.toGameDBStage()
            })
        }
        return gameStageDao.getList().map {
            it.toGameStage()
        }
    }

    override suspend fun updateGameStageStatus(map: HashMap<Int, Int>): List<GameStage> {
        return gameStageDao.updateStatusAndReturnList(map).map { it.toGameStage() }
    }

    override suspend fun insertGameStage(gameStage: GameStage): Long {
        return gameStageDao.create(gameStage.toGameDBStage())
    }

    override suspend fun getGameStageList(): List<GameStage> {
        return gameStageDao.getList().map {
            it.toGameStage()
        }
    }

    override suspend fun getGameStage(gameStageId: Int): GameStage {
        return gameStageDao.getGameStage(gameStageId).toGameStage()
    }
}