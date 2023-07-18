package com.jhlee.rongame.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhlee.rongame.data.local.entity.DBCard

@Dao
interface DBCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(coin: DBCard): Long

    @Query("SELECT * FROM DBCard")
    suspend fun getCardList(): List<DBCard>


    @Query("DELETE FROM DBCard WHERE id = :cardId")
    suspend fun deleteCardById(cardId: Int)

    @Query("DELETE FROM DBCard WHERE id IN (:cardIds)")
    suspend fun deleteCardByIds(cardIds: List<Int>)
}