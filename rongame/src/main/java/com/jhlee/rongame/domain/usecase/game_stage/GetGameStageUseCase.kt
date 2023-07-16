package com.jhlee.rongame.domain.usecase.game_stage

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.GameStage
import com.jhlee.rongame.domain.repository.GameStageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameStageUseCase @Inject constructor(private val gameStageRepository: GameStageRepository) {
    operator fun invoke(stageId: Int): Flow<Resource<GameStage>> = flow {
        emit(Resource.Loading<GameStage>())
        try {
            emit(Resource.Success<GameStage>(gameStageRepository.getGameStage(stageId)))
        } catch (e: Exception) {
            emit(Resource.Error<GameStage>(e.message.toString()))
        }
    }
}