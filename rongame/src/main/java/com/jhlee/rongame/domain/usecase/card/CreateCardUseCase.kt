package com.jhlee.rongame.domain.usecase.card

import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.common.constants.GatchaConst
import com.jhlee.rongame.common.constants.HeroConst
import com.jhlee.rongame.data.local.InitData
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CreateCardUseCase @Inject constructor(
    private val repository: CardRepository<Card>
) {
    operator fun invoke(): Flow<Resource<Card>> = flow {
        try {
            var count = GatchaConst.DELAY_TIME
            val offsetTime: Long = 50
            while (count > 0) {
                emit(Resource.Loading<Card>())
                kotlinx.coroutines.delay(offsetTime)
                count = count.minus(offsetTime.toInt())
            }

            val hero = HeroConst.HERO_LIST[(Math.random() * InitData.heroList.size).toInt()]
            val cost = when ((Math.random() * 101).toInt()) {
                in 1..10 -> 1
                in 11..50 -> 2
                in 51..80 -> 3
                in 81..90 -> 4
                else -> 5
            }
            val grade = when ((Math.random() * 100).toInt()) {
                in 1..49 -> 0
                in 50..74 -> 1
                in 75..89 -> 2
                in 90..94 -> 3
                in 95..97 -> 4
                in 98..99 -> 5
                else -> 6
            }
            val card = Card(0, name = hero.name, cost = cost, grade = grade, hero = hero)
            emit(Resource.Success<Card>(repository.createCard(card)))

        } catch (e: IOException) {
            emit(Resource.Error<Card>(e.localizedMessage as String))
        }
    }
}