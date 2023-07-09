package com.jhlee.rongame.common

import com.jhlee.rongame.domain.model.Card

class Utils {

    companion object {
        fun getPower(card: Card): Int {
            val hero = card.hero
            return (hero.attack + hero.defense + hero.speed + hero.hp + hero.mp)
        }
    }
}