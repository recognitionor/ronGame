package com.jhlee.rongame.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.jhlee.quiz_libs.domain.model.Quiz

@Entity
data class DBQuiz(
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "level") val level: Int,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "answer") val answer: Int,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "choiceList") val choiceList: String,
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "chance") val chance: Int,
    @ColumnInfo(name = "reward") val reward: Int,
    @ColumnInfo(name = "status") val status: Boolean,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

fun DBQuiz.toQuiz(): Quiz {
    val choiceList = Gson().fromJson(choiceList, Array<String>::class.java)
    return Quiz(
        id,
        category,
        level,
        imageUrl,
        answer,
        question,
        choiceList.toMutableList(),
        time,
        chance,
        reward
    )
}