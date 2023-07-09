package com.jhlee.rongame.domain.usecase.hero

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.common.constants.GatchaConst
import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.repository.HeroRepository
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateHeroUseCase @Inject constructor(private val repository: HeroRepository<Hero>) {
    operator fun invoke(list: List<Hero>): Flow<Resource<List<Hero>>> = flow {
        try {
            var count = GatchaConst.DELAY_TIME
            while (count > 0) {
                delay(1)
                emit(Resource.Loading<List<Hero>>())
                count = count.minus(10)
            }


            repository.createList(list)
            val result = repository.getHeroList()
            emit(Resource.Success<List<Hero>>(result))
        } catch (e: Exception) {
            emit(Resource.Error<List<Hero>>(e.message ?: "get hero error"))
        }
    }
}