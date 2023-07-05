package com.jhlee.rongame.domain.repository

interface HeroRepository<Hero> {
    suspend fun createList(list: List<Hero>)
    suspend fun getHeroList(): List<Hero>
}