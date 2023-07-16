package com.jhlee.rongame.domain.usecase.game_stage

import android.util.Log
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.GameStage
import com.jhlee.rongame.domain.repository.GameStageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertGameStageListUseCase @Inject constructor(private val gameStageRepository: GameStageRepository) {
    operator fun invoke(list: List<GameStage>? = null): Flow<Resource<List<GameStage>>> = flow {
        emit(Resource.Loading<List<GameStage>>())
        try {
            val result = gameStageRepository.insertGameStageList(list)
            emit(Resource.Success<List<GameStage>>(result))
        } catch (e: Exception) {
            emit(Resource.Error<List<GameStage>>(e.message.toString()))
        }
    }
}