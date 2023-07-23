package com.jhlee.rongame.domain.usecase.game_stage

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.GameStage
import com.jhlee.rongame.domain.repository.GameStageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateGameStageListUseCase @Inject constructor(private val gameStageRepository: GameStageRepository) {
    operator fun invoke(map: HashMap<Int, Int>): Flow<Resource<List<GameStage>>> = flow {
        emit(Resource.Loading<List<GameStage>>())
        try {
            val result = gameStageRepository.updateGameStageStatus(map)
            emit(Resource.Success<List<GameStage>>(result))
        } catch (e: Exception) {
            emit(Resource.Error<List<GameStage>>(e.message.toString()))
        }
    }
}