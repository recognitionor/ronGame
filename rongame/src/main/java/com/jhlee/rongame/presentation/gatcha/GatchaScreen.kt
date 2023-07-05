package com.jhlee.rongame.presentation.gatcha

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.R
import com.jhlee.rongame.presentation.card.CardScreen
import com.jhlee.rongame.presentation.card.CardViewModel
import com.jhlee.rongame.presentation.user.UserInfoViewModel

@Composable
fun GatchaScreen(viewModel: CardViewModel = hiltViewModel()) {
    val ctx = LocalContext.current
    Column {
        Button(onClick = {
            viewModel.gatchaCard()
        }) {
            Text(text = ctx.getString(R.string.card_gatcha_title))
        }
        CardScreen(height = 300f)
    }

}