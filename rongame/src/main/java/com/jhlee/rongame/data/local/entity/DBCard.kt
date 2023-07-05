package com.jhlee.rongame.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.model.UserInfo

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = DBHero::class,
            parentColumns = ["id"],
            childColumns = ["heroId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DBCard(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cost") val cost: Int,
    @ColumnInfo(name = "grade") val grade: Int,
    @ColumnInfo(name = "heroId") val heroId: Int,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

fun DBCard.toCard(hero: Hero): Card {
    return Card(id, name, cost, grade, hero)
}