package com.jhlee.rongame.common.utils

import com.jhlee.rongame.domain.model.Card

class GameUtils {

    companion object {
        fun getPower(card: Card): Int {
            return (card.attack + card.defense + card.speed + card.hp + card.mp)
        }
    }
}