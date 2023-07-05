package com.jhlee.rongame.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jhlee.rongame.domain.model.UserInfo

@Entity
data class DBUserInfo(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "money") val money: Int,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

fun DBUserInfo.toUser(): UserInfo {
    return UserInfo(id, name, money)
}