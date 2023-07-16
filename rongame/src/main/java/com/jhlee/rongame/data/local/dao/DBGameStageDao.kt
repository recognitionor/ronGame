package com.jhlee.rongame.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhlee.rongame.data.local.entity.DBCard
import com.jhlee.rongame.data.local.entity.DBGameStage
import com.jhlee.rongame.domain.model.GameStage

@Dao
interface DBGameStageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createList(gameStageList: List<DBGameStage>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(gameStageList: DBGameStage): Long

    @Query("SELECT * FROM DBGameStage")
    suspend fun getList(): List<DBGameStage>

    @Query("SELECT * FROM DBGameStage WHERE id = :id")
    suspend fun getGameStage(id: Int): DBGameStage
}