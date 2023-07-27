package com.jhlee.rongame.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.R
import com.jhlee.rongame.presentation.card.CardScreen
import com.jhlee.rongame.presentation.card.CardViewModel
import com.jhlee.rongame.presentation.card_list.CardListScreen
import com.jhlee.rongame.presentation.card_list.CardListViewModel
import com.jhlee.rongame.presentation.user.UserInfoScreen
import com.jhlee.rongame.presentation.user.UserInfoViewModel

@Composable
fun HomeScreen() {
    val ctx = LocalContext.current
    val userInfoViewModel: UserInfoViewModel = hiltViewModel()
    val cardViewModel: CardViewModel = hiltViewModel()
    val cardListViewModel: CardListViewModel = hiltViewModel()
    if (cardViewModel.state.value.isLoadDone) {
        cardListViewModel.getCardList()
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 70.dp, start = 16.dp, end = 16.dp)
    ) {
        UserInfoScreen(userInfoViewModel)
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            CardScreen(cardViewModel, userInfoViewModel, height = 250f)
        }

        Text(
            text = ctx.getString(R.string.card_list_title), fontSize = 24.sp, style = TextStyle(
                fontFamily = FontFamily(Font(R.font.yeongdeok_sea))
            ), modifier = Modifier.padding(bottom = 4.dp)
        )
        CardListScreen(cardListViewModel)

    }
}