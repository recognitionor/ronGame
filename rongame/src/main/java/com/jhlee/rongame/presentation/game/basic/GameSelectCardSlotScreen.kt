package com.jhlee.rongame.presentation.game.basic

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jhlee.rongame.domain.const.GameConst
import com.jhlee.rongame.domain.model.Card

@Composable
fun GameSelectCardSlotScreen(
    selectedCard: List<MutableState<Card?>>, selectedType: MutableState<Int>
) {
    val ctx = LocalContext.current
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            GameSlotView(
                GameConst.GAME_SELECTED_CARD_TYPE_ATT,
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_ATT].value,
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = if (selectedType.value == 0) 3.dp else 1.dp,
                        color = if (selectedType.value == 0) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                selectedType.value = GameConst.GAME_SELECTED_CARD_TYPE_ATT
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_ATT].value = null
            }
            GameSlotView(
                GameConst.GAME_SELECTED_CARD_TYPE_DEF,
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_DEF].value,
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = if (selectedType.value == 1) 3.dp else 1.dp,
                        color = if (selectedType.value == 1) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                selectedType.value = GameConst.GAME_SELECTED_CARD_TYPE_DEF
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_DEF].value = null
            }
            GameSlotView(
                GameConst.GAME_SELECTED_CARD_TYPE_SPD,
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_SPD].value,
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = if (selectedType.value == 2) 3.dp else 1.dp,
                        color = if (selectedType.value == 2) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(5.dp)
            ) {
                selectedType.value = GameConst.GAME_SELECTED_CARD_TYPE_SPD
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_SPD].value = null
            }
            GameSlotView(
                GameConst.GAME_SELECTED_CARD_TYPE_HP,
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_HP].value,
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = if (selectedType.value == 3) 3.dp else 1.dp,
                        color = if (selectedType.value == 3) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                selectedType.value = GameConst.GAME_SELECTED_CARD_TYPE_HP
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_HP].value = null

            }
            GameSlotView(
                GameConst.GAME_SELECTED_CARD_TYPE_MP,
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_MP].value,
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = if (selectedType.value == 4) 3.dp else 1.dp,
                        color = if (selectedType.value == 4) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                selectedType.value = GameConst.GAME_SELECTED_CARD_TYPE_MP
                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_MP].value = null
            }
        }
    }
}