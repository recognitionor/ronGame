package com.jhlee.rongame.domain.usecase.game_stage

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.GameStage
import com.jhlee.rongame.domain.repository.GameStageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetGameStageListUseCase @Inject constructor(private val gameStageRepository: GameStageRepository) {
    operator fun invoke(): Flow<Resource<List<GameStage>>> = flow {
        emit(Resource.Loading<List<GameStage>>())
        try {
            emit(Resource.Success<List<GameStage>>(gameStageRepository.getGameList()))
        } catch (e: Exception) {
            emit(Resource.Error<List<GameStage>>(e.message.toString()))
        }
    }
}