package com.jhlee.rongame.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhlee.rongame.data.local.entity.DBAttend
import com.jhlee.rongame.data.local.entity.DBCard
import com.jhlee.rongame.data.local.entity.DBGameStage

@Dao
interface DBAttendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAttend(attend: DBAttend)

    @Query("SELECT * FROM DBAttend")
    suspend fun getAttendList(): List<DBAttend>


}