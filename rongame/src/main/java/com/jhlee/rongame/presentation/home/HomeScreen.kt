package com.jhlee.rongame.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.presentation.card.CardScreen
import com.jhlee.rongame.presentation.card.CardViewModel
import com.jhlee.rongame.presentation.user.UserInfoScreen
import com.jhlee.rongame.presentation.user.UserInfoViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val userInfoViewModel: UserInfoViewModel = hiltViewModel()
    val cardViewModel: CardViewModel = hiltViewModel()
    Column {
        UserInfoScreen(userInfoViewModel)
        CardScreen(cardViewModel, userInfoViewModel, height = 300f)
    }
}