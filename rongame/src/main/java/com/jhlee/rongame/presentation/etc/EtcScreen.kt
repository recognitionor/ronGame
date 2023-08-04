package com.jhlee.rongame.presentation.etc

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.quiz_libs.QuizActivity
import com.jhlee.rongame.R
import com.jhlee.rongame.common.utils.Utils

@Composable
fun EtcScreen() {
    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->

        })
    val attendViewModel: AttendViewModel = hiltViewModel()
    val ctx = LocalContext.current
    Column {
        CalendarScreen(attendViewModel)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Button(modifier = Modifier.fillMaxWidth(),
                enabled = !attendViewModel.state.value.dateList.contains(Utils.getCurrentDateInFormat()),
                onClick = {
                    attendViewModel.createAttend(System.currentTimeMillis())
                }) {
                Text(text = ctx.getString(R.string.etc_attend_btn))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    resultLauncher.launch(Intent(ctx, QuizActivity::class.java))
                }) {
                Text(text = ctx.getString(R.string.etc_mini_game_quiz))
            }
        }
    }
}