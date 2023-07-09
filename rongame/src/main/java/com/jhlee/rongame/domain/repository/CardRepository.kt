package com.jhlee.rongame.domain.repository

interface CardRepository<Card> {
    suspend fun createCard(card: Card): Card
    suspend fun deleteCard(card: Card)
    suspend fun updateCard(card: Card): Card
    suspend fun getCardList(): List<Card>
}