package com.jhlee.rongame.presentation.etc

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.quiz_libs.QuizActivity
import com.jhlee.quiz_libs.domain.model.Quiz
import com.jhlee.rongame.R
import com.jhlee.rongame.common.utils.Utils
import com.jhlee.rongame.presentation.game.quiz.QuizResultDialog

@Composable
fun EtcScreen() {
    val etcViewModel: EtcViewModel = hiltViewModel()
    val ctx = LocalContext.current
    val showInfoDialog = remember { mutableStateOf(false) }
    val quizResultList: MutableState<List<Quiz>> = remember {
        mutableStateOf(listOf())
    }

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val quizList: ArrayList<Quiz>? =
                result.data?.extras?.getParcelableArrayList(QuizActivity.QUIZ_ANSWER_EXTRA)
            quizList?.let {
                quizResultList.value = it
                showInfoDialog.value = true
            }
        })

    Column {
        CalendarScreen(etcViewModel)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Button(modifier = Modifier.fillMaxWidth(),
                enabled = !etcViewModel.state.value.dateList.contains(Utils.getCurrentDateInFormat()),
                onClick = {
                    etcViewModel.createAttend(System.currentTimeMillis())
                }) {
                Text(text = ctx.getString(R.string.etc_attend_btn))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                resultLauncher.launch(Intent(ctx, QuizActivity::class.java).apply {
                    val list: ArrayList<Quiz> = arrayListOf()
                    list.addAll(etcViewModel.state.value.quizList)
                    this.putExtra(QuizActivity.QUIZ_LIST_EXTRA, list)
                })
            }) {
                Text(text = ctx.getString(R.string.etc_mini_game_quiz))
            }

            // 다이얼로그 표시
            if (showInfoDialog.value) {
                QuizResultDialog(quizResultList) {
                    showInfoDialog.value = false
                    etcViewModel.rewardUpdate(it)
                    etcViewModel.getQuizList()
                }
            }
        }
    }
}