package com.jhlee.rongame.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jhlee.rongame.R
import com.jhlee.rongame.domain.const.GameConst
import com.jhlee.rongame.domain.model.Card

@Composable
fun GameSlotView(cardType: Int, card: Card? = null, modifier: Modifier, itemClick: () -> Unit) {
    val ctx = LocalContext.current
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable { itemClick() }
            .fillMaxHeight()
            .padding(end = 2.dp)
            .background(Color.White)) {
        var cardTypeText = ""
        val temp = when (cardType) {
            GameConst.GAME_SELECTED_CARD_TYPE_ATT -> {
                cardTypeText = ctx.getString(R.string.game_att)
                card?.attack ?: 0
            }

            GameConst.GAME_SELECTED_CARD_TYPE_DEF -> {
                cardTypeText = ctx.getString(R.string.game_def)
                card?.defense ?: 0
            }

            GameConst.GAME_SELECTED_CARD_TYPE_SPD -> {
                cardTypeText = ctx.getString(R.string.game_speed)
                card?.speed ?: 0
            }

            GameConst.GAME_SELECTED_CARD_TYPE_HP -> {
                cardTypeText = ctx.getString(R.string.game_hp)
                card?.hp ?: 0
            }

            else -> {
                cardTypeText = ctx.getString(R.string.game_mp)
                card?.mp ?: 0
            }

        }
        Column {
            Text(text = cardTypeText)
            Text(text = temp.toString())
        }

    }
}