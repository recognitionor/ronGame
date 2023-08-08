package com.jhlee.rongame.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jhlee.quiz_libs.domain.model.Quiz
import com.jhlee.rongame.data.local.entity.DBQuiz

@Dao
interface DBQuizDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuizList(quizList: List<DBQuiz>): List<Long>

    @Query("SELECT * FROM DBQuiz WHERE status = 0 ORDER BY RANDOM() LIMIT 3")
    suspend fun getQuizList(): List<DBQuiz>

    @Update
    suspend fun updateQuiz(quiz: DBQuiz)
}