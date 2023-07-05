package com.jhlee.rongame.data.repository

import com.jhlee.rongame.data.local.dao.DBUserInfoDao
import com.jhlee.rongame.data.local.entity.DBUserInfo
import com.jhlee.rongame.data.local.entity.toUser
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dao: DBUserInfoDao) :
    UserRepository<UserInfo> {

    override suspend fun createCard(): UserInfo {
        val userInfo = DBUserInfo("이로운", 1000)
        dao.insertUserInfo(userInfo)
        return userInfo.toUser()
    }

    override suspend fun getCard(): UserInfo {
        return dao.getUserInfo().toUser()

    }

    override suspend fun updateCard(user: UserInfo): UserInfo {
        val userInfo = DBUserInfo(user.name, user.money)
        dao.insertUserInfo(userInfo)
        return userInfo.toUser()
    }
}