package com.jhlee.rongame.domain.usecase.card

import android.util.Log
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.common.constants.GatchaConst
import com.jhlee.rongame.common.constants.HeroConst
import com.jhlee.rongame.data.local.InitData
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.Collections
import javax.inject.Inject
import kotlin.random.Random

class CreateCardUseCase @Inject constructor(
    private val repository: CardRepository<Card>
) {

    private fun distributePoints(grade: Int, hero: Hero): Hero {
        var totalPoint = 0
        when (grade) {
            0 -> totalPoint = 10
            1 -> totalPoint = 15
            2 -> totalPoint = 20
            3 -> totalPoint = 30
            4 -> totalPoint = 45
            5 -> totalPoint = 60
            6 -> totalPoint = 80
        }
        var remainPoint = totalPoint
        val pointList = arrayListOf<Int>()
        for (i in 0..4) {
            var random = 0
            random = if (i == 0) {
                Random.Default.nextInt((remainPoint.toFloat() / 1.5).toInt())
            } else {
                Random.Default.nextInt(remainPoint)
            }
            remainPoint = remainPoint.minus(random)
            pointList.add(random)
        }
        pointList.shuffle()
        return hero.copy(
            attack = pointList[0] ?: 0,
            defense = pointList[1] ?: 0,
            speed = pointList[2] ?: 0,
            hp = pointList[3] ?: 0,
            mp = pointList[4] ?: 0,
        )
    }

    operator fun invoke(): Flow<Resource<Card>> = flow {
        try {
            emit(Resource.Loading<Card>())
            var count = GatchaConst.DELAY_TIME
            val offsetTime: Long = 50
            while (count > 0) {
                emit(Resource.Loading<Card>())
                kotlinx.coroutines.delay(offsetTime)
                count = count.minus(offsetTime.toInt())
            }

            var hero = HeroConst.HERO_LIST[(Math.random() * InitData.heroList.size).toInt()]
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
            hero = distributePoints(grade, hero)
            Log.d("jhlee", "hero : $hero")

            val card = Card(0, name = hero.name, cost = cost, grade = grade, hero = hero)
            emit(Resource.Success<Card>(repository.createCard(card)))

        } catch (e: IOException) {
            emit(Resource.Error<Card>(e.localizedMessage as String))
        }
    }
}