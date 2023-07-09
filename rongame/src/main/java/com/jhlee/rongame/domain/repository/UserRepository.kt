package com.jhlee.rongame.domain.repository

interface UserRepository<T> {
    suspend fun createUserInfo(user: T): T
    suspend fun updateUserInfo(user: T): T
    suspend fun getUserInfo(): T
}