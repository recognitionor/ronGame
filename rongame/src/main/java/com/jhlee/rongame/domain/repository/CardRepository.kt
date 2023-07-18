package com.jhlee.rongame.domain.repository

interface CardRepository<Card> {
    suspend fun createCard(card: Card): Card
    suspend fun deleteCard(ids: Int)
    suspend fun deleteCardList(idList: List<Int>)
    suspend fun updateCard(card: Card): Card
    suspend fun getCardList(): List<Card>
}