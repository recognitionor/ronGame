package com.jhlee.rongame.domain.repository

import com.jhlee.quiz_libs.domain.model.Quiz

interface QuizRepository {
    suspend fun insertQuiz(list: List<Quiz>)

    suspend fun updateQuiz(quiz: Quiz)

    suspend fun getQuizList(limit: Int): List<Quiz>

    suspend fun getQuizListAll(): List<Quiz>
}