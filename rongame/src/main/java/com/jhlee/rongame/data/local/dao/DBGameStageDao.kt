package com.jhlee.rongame.data.local.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.jhlee.rongame.data.local.entity.DBGameStage

@Dao
interface DBGameStageDao {

    @Update
    suspend fun update(gameStage: DBGameStage)

    @Query("UPDATE DBGameStage SET status = :newStatus WHERE id = :id")
    suspend fun updateStatus(id: Int, newStatus: Int)

    @Transaction
    suspend fun updateStatusAndReturnList(map: HashMap<Int, Int>): List<DBGameStage> {
        map.forEach {
            updateStatus(it.key, it.value)
        }
        return getList()
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createList(gameStageList: List<DBGameStage>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(gameStageList: DBGameStage): Long

    @Query("SELECT * FROM DBGameStage")
    suspend fun getList(): List<DBGameStage>

    @Query("SELECT * FROM DBGameStage WHERE id = :id")
    suspend fun getGameStage(id: Int): DBGameStage
}