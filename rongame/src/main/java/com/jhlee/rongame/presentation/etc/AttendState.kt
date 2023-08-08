package com.jhlee.rongame.presentation.etc

import com.jhlee.quiz_libs.domain.model.Quiz

data class AttendState(
    val dateList: List<String> = emptyList(),
    val quizList: List<Quiz> = emptyList(),
    val error: String = "",
    val progress: Int = 0
)