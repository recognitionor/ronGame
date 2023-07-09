package com.jhlee.rongame.domain.model

data class GameStage(
    val id: Int,
    val cost: Int,
    val reward: Int,
    val status: Int,
    val type: Int,
    val att: Int,
    val def: Int,
    val spd: Int,
    val hp: Int,
    val mp: Int
)
