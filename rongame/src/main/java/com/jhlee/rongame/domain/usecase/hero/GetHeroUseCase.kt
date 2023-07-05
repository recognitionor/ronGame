package com.jhlee.rongame.domain.usecase.hero

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.repository.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHeroUseCase @Inject constructor(private val repository: HeroRepository<Hero>) {

    operator fun invoke(): Flow<Resource<List<Hero>>> = flow {
        try {
            emit(Resource.Loading<List<Hero>>())
            kotlinx.coroutines.delay(1000)
            val result = repository.getHeroList()
            emit(Resource.Success<List<Hero>>(result))
        } catch (e: Exception) {
            emit(Resource.Error<List<Hero>>(e.message ?: "get hero error"))
        }
    }
}