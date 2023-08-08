package com.jhlee.rongame.data.repository

import com.google.gson.Gson
import com.jhlee.quiz_libs.domain.model.Quiz
import com.jhlee.rongame.data.local.dao.DBQuizDao
import com.jhlee.rongame.data.local.entity.DBQuiz
import com.jhlee.rongame.data.local.entity.toQuiz
import com.jhlee.rongame.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(private val dao: DBQuizDao) : QuizRepository {
    override suspend fun insertQuiz(list: List<Quiz>) {
       val tempList =  list.map { quiz ->
            DBQuiz(
                id = quiz.id,
                category = quiz.category,
                reward = quiz.reward,
                chance = quiz.chance,
                answer = quiz.answer,
                choiceList = Gson().toJson(quiz.choiceList),
                imageUrl = quiz.imageUrl,
                question = quiz.question,
                level = quiz.level,
                time = quiz.time,
                status = false
            )
        }
        dao.insertQuizList(tempList)
    }

    override suspend fun updateQuiz(quiz: Quiz) = dao.updateQuiz(
        DBQuiz(
            id = quiz.id,
            category = quiz.category,
            reward = quiz.reward,
            chance = quiz.chance,
            answer = quiz.answer,
            choiceList = Gson().toJson(quiz.choiceList),
            imageUrl = quiz.imageUrl,
            question = quiz.question,
            level = quiz.level,
            time = quiz.time,
            status = true
        )
    )


    override suspend fun getQuizList(): List<Quiz> = dao.getQuizList().map { it.toQuiz() }
}