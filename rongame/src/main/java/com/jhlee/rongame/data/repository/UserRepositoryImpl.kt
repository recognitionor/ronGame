package com.jhlee.rongame.data.repository

import com.jhlee.rongame.data.local.dao.DBUserInfoDao
import com.jhlee.rongame.data.local.entity.DBUserInfo
import com.jhlee.rongame.data.local.entity.toUser
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dao: DBUserInfoDao) :
    UserRepository<UserInfo> {

    override suspend fun createUserInfo(user: UserInfo): UserInfo {
        dao.insertUserInfo(DBUserInfo(name = user.name, money = user.money))
        return dao.getUserInfo().toUser()
    }

    override suspend fun getUserInfo(): UserInfo {
        return dao.getUserInfo().toUser()
    }

    override suspend fun updateUserInfo(user: UserInfo): UserInfo {
        val userInfo = DBUserInfo(id = user.id, name = user.name, money = user.money)
        dao.updateUserInfo(userInfo)
        return dao.getUserInfo().toUser()
    }
}