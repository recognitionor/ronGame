package com.jhlee.rongame.presentation.game.quiz

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(stageId: Int) {
    val ctx = LocalContext.current
    val quizViewModel: QuizViewModel = hiltViewModel()
    Text(text = "test")
}