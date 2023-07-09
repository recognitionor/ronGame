package com.jhlee.rongame.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jhlee.rongame.data.local.entity.DBUserInfo

@Dao
interface DBUserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(user: DBUserInfo)

    @Query("SELECT * FROM DBUserInfo")
    suspend fun getUserInfo(): DBUserInfo

    @Update
    suspend fun updateUserInfo(user: DBUserInfo): Int
}