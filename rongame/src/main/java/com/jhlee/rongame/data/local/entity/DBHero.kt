package com.jhlee.rongame.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.model.UserInfo

@Entity(indices = [Index(value = ["name"], unique = true)])
data class DBHero(
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "attack") val attack: Int,
    @ColumnInfo(name = "defense") val defense: Int,
    @ColumnInfo(name = "speed") val speed: Int,
    @ColumnInfo(name = "hp") val hp: Int,
    @ColumnInfo(name = "mp") val mp: Int,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

fun DBHero.toHero(): Hero {
    return Hero(id, image, type, name, attack, defense, speed, hp, mp)
}

object DBHeroMapper {
    fun mapToDB(hero: Hero): DBHero {
        return DBHero(
            hero.type,
            hero.name,
            hero.image,
            hero.attack,
            hero.defense,
            hero.speed,
            hero.hp,
            hero.mp
        )
    }

    fun mapFromDB(dbHero: DBHero): Hero {
        return Hero(
            dbHero.id,
            dbHero.image,
            dbHero.type,
            dbHero.name,
            dbHero.attack,
            dbHero.defense,
            dbHero.speed,
            dbHero.hp,
            dbHero.mp
        )
    }
}