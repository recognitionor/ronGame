package com.jhlee.rongame.presentation.etc

data class AttendState(
    val dateList: List<String> = emptyList(),
    val error: String = "",
    val progress: Int = 0
)