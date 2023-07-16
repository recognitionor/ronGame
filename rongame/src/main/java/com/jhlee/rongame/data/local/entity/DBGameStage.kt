package com.jhlee.rongame.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jhlee.rongame.domain.model.GameStage

@Entity
data class DBGameStage(
    @ColumnInfo(name = "cost") val cost: Int,
    @ColumnInfo(name = "reward") val reward: Int,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "type") val type: Int,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

fun DBGameStage.toGameStage(): GameStage {
    return GameStage(id, cost, reward, image, status, type)
}

fun GameStage.toGameDBStage(): DBGameStage {
    return DBGameStage(
        id = id, cost = cost, reward = reward, image = image, status = status, type = type
    )
}