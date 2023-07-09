package com.jhlee.rongame.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jhlee.rongame.domain.model.Card

@Entity
data class DBCard(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cost") val cost: Int,
    @ColumnInfo(name = "grade") val grade: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "attack") val attack: Int,
    @ColumnInfo(name = "defense") val defense: Int,
    @ColumnInfo(name = "speed") val speed: Int,
    @ColumnInfo(name = "hp") val hp: Int,
    @ColumnInfo(name = "mp") val mp: Int,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

fun DBCard.toCard(): Card {
    return Card(id, name, cost, grade, image, type, attack, defense, speed, hp, mp)
}