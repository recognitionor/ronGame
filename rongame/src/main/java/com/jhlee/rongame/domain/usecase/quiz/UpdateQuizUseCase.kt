package com.jhlee.rongame.domain.usecase.quiz

import com.jhlee.quiz_libs.domain.model.Quiz
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.data.repository.DBQuizRepositoryImpl
import com.jhlee.rongame.data.repository.LocalRepository
import com.jhlee.rongame.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateQuizUseCase @Inject constructor(@LocalRepository private val quizRepository: QuizRepository) {
    operator fun invoke(quizList: List<Quiz>): Flow<Resource<List<Quiz>>> = flow {
        emit(Resource.Loading<List<Quiz>>())
        try {
//            val result = gameStageRepository.updateGameStageStatus(map)
//            emit(Resource.Success<List<Quiz>>(result))
        } catch (e: Exception) {
            emit(Resource.Error<List<Quiz>>(e.message.toString()))
        }
    }
}