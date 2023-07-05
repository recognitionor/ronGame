package com.jhlee.rongame.domain.model

data class Hero(
    val id: Int,
    val image: String,
    val type: String,
    val name: String,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val hp: Int,
    val mp: Int,
)