package com.jhlee.rongame.data.repository

import android.util.Log
import com.jhlee.rongame.data.local.dao.DBHeroDao
import com.jhlee.rongame.data.local.entity.DBHeroMapper
import com.jhlee.rongame.data.local.entity.toHero
import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.repository.HeroRepository
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(private val dao: DBHeroDao) : HeroRepository<Hero> {

    override suspend fun createList(list: List<Hero>) {
        val result = list.map {
            DBHeroMapper.mapToDB(it)
        }
        dao.insertHero(result)
    }

    override suspend fun getHeroList(): List<Hero> {
        val list = dao.getHeroList().map {
            it.toHero()
        }
        return list
    }
}