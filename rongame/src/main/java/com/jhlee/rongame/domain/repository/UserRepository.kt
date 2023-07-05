package com.jhlee.rongame.domain.repository

interface UserRepository<T> {
    suspend fun createCard(): T
    suspend fun updateCard(user: T): T
    suspend fun getCard(): T
}