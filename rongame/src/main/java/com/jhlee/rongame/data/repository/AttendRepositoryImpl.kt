package com.jhlee.rongame.data.repository

import android.util.Log
import com.jhlee.rongame.common.utils.Utils
import com.jhlee.rongame.data.local.dao.DBAttendDao
import com.jhlee.rongame.data.local.entity.DBAttend
import com.jhlee.rongame.domain.repository.AttendRepository
import javax.inject.Inject

class AttendRepositoryImpl @Inject constructor(private val attendDao: DBAttendDao) :
    AttendRepository {
    override suspend fun createAttend(date: Long) {
        attendDao.createAttend(DBAttend(Utils.getCurrentDateInFormat(date = date)))
    }

    override suspend fun getAttendList(): List<String> {
        return attendDao.getAttendList().map { it.data }
    }
}