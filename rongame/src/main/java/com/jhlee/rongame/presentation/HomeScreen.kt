package com.jhlee.rongame.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jhlee.rongame.presentation.gatcha.GatchaScreen
import com.jhlee.rongame.presentation.user.UserInfoScreen

@Composable
fun HomeScreen() {
    Column {
        UserInfoScreen()
        GatchaScreen()
    }
}