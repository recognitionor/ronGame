package com.jhlee.rongame.presentation.game.quiz

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.jhlee.quiz_libs.domain.model.Quiz
import com.jhlee.rongame.R

@Composable
fun QuizResultDialog(quizResultList: MutableState<List<Quiz>>, confirmBtn: (reward: Int) -> Unit) {
    val correctCount = remember { mutableStateOf(0) }
    val rewardScore = remember { mutableStateOf(0) }
    quizResultList.value.forEach {
        if (it.answer == it.selected) {
            correctCount.value = correctCount.value.plus(1)
            rewardScore.value = rewardScore.value.plus(it.reward)
        }
    }
    val ctx = LocalContext.current
    AlertDialog(onDismissRequest = { confirmBtn(rewardScore.value) }, title = {
        Text(
            text = ctx.getString(
                R.string.etc_result_dialog_title, quizResultList.value.size, correctCount.value
            )
        )
    }, text = {
        Column {
            Text(
                text = ctx.getString(
                    R.string.etc_result_dialog_content, rewardScore.value
                )
            )
        }
    }, confirmButton = {
        Button(onClick = {
            confirmBtn(rewardScore.value)
        }) {
            Text(text = ctx.getString(R.string.confirm))
        }
    })
}
