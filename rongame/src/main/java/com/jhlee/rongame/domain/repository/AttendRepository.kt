package com.jhlee.rongame.domain.repository

interface AttendRepository {
    suspend fun createAttend(date: Long)

    suspend fun getAttendList(): List<String>

}