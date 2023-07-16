package com.jhlee.rongame.domain.model

data class GameStage(
    val id: Int,
    val cost: Int,
    val reward: Int,
    val image: String,
    val status: Int,
    val type: Int,
)
