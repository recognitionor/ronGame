package com.jhlee.rongame.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["data"], unique = true)])
data class DBAttend(
    @ColumnInfo(name = "data") val data: String,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
)