package com.jhlee.rongame.domain.model

data class Card(
    var id: Int, val name: String, val cost: Int, val grade: Int, val hero: Hero
)

