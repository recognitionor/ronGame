package com.jhlee.rongame.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jhlee.rongame.data.local.entity.DBHero
import com.jhlee.rongame.data.local.entity.DBUserInfo

@Dao
interface DBHeroDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertHero(list: List<DBHero>) : List<Long>

    @Query("SELECT * FROM DBHero")
    suspend fun getHeroList(): List<DBHero>


}