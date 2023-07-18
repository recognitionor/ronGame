package com.jhlee.rongame.data.repository

import android.util.Log
import com.jhlee.rongame.data.local.dao.DBCardDao
import com.jhlee.rongame.data.local.entity.DBCard
import com.jhlee.rongame.data.local.entity.toCard
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardDao: DBCardDao
) : CardRepository<Card> {
    override suspend fun createCard(card: Card): Card {
        val id = cardDao.create(
            DBCard(
                card.name,
                card.cost,
                card.grade,
                card.image,
                card.type,
                card.attack,
                card.defense,
                card.speed,
                card.hp,
                card.mp
            )
        )
        card.id = id.toInt()
        return card
    }

    override suspend fun deleteCard(ids: Int) = cardDao.deleteCardById(ids)

    override suspend fun deleteCardList(idList: List<Int>) = cardDao.deleteCardByIds(idList)
    override suspend fun getCardList(): List<Card> {
        return cardDao.getCardList().map {
            it.toCard()
        }.reversed()
    }

    override suspend fun updateCard(card: Card): Card {
        return card
    }

}