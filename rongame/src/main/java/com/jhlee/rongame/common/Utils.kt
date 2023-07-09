package com.jhlee.rongame.common

import com.jhlee.rongame.domain.model.Card

class Utils {

    companion object {
        fun getPower(card: Card): Int {
            return (card.attack + card.defense + card.speed + card.hp + card.mp)
        }
    }
}