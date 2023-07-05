package com.jhlee.rongame.data.repository

import android.util.Log
import com.jhlee.rongame.data.local.dao.DBCardDao
import com.jhlee.rongame.data.local.dao.DBHeroDao
import com.jhlee.rongame.data.local.entity.DBCard
import com.jhlee.rongame.data.local.entity.DBHeroMapper
import com.jhlee.rongame.data.local.entity.toCard
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardDao: DBCardDao, private val heroDao: DBHeroDao
) : CardRepository<Card> {

    override suspend fun createCard(card: Card): Card {
        val id = cardDao.create(DBCard(card.name, card.cost, card.grade, card.hero.id))
        card.id = id.toInt()
        return card
    }

    override suspend fun getCard(): List<Card> {
        return emptyList()
    }

    override suspend fun updateCard(card: Card): Card {
        return card
    }

    override suspend fun deleteCard(card: Card) {
        Log.d("jhlee", "deleteCard")
    }
}