package com.jhlee.rongame.presentation.game.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.R
import com.jhlee.rongame.common.constants.RuleConst
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.presentation.card_list.CardListItemScreen

@Composable
fun GameScreen(stageId: Int, activityFinish: () -> Unit) {
    val ctx = LocalContext.current
    val gameViewModel: GameViewModel = hiltViewModel()
    val gameBattleViewModel: GameBattleViewModel = hiltViewModel()

    val gameState: GameState = gameViewModel.state.value
    val gameBattleState = gameBattleViewModel.state.value
    val selectedType = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        gameViewModel.getCardList()
        gameViewModel.getGameStage(stageId)
    }
    val selectedCard: List<MutableState<Card?>> = remember {
        listOf(
            mutableStateOf(null),
            mutableStateOf(null),
            mutableStateOf(null),
            mutableStateOf(null),
            mutableStateOf(null)
        )
    }
    if (gameState.isGameStageLoadDone) {
        gameState.selectedGameStage?.let {
            gameViewModel.setGameStageLoadFlag(false)
            gameBattleViewModel.initGameStage(it)
        }
    }

    when (gameBattleState.viewMode) {
        GameBattleState.VIEW_MODE_GAME_LOSE_RESULT -> {
            gameViewModel.deleteCardList(gameState.selectedCardList.mapNotNull { it?.id })
        }

        GameBattleState.VIEW_MODE_FINISH -> {
            activityFinish.invoke()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(2f)
                        .height(180.dp)
                        .padding(end = 2.dp)
                        .background(Color.White)
                        .border(width = 2.dp, color = Color.Green, shape = RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = ctx.getString(
                            R.string.game_my_info
                        ),
                        fontSize = 20.sp,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                    )
                    Box(Modifier.height(6.dp))

                    Text(
                        text = ctx.getString(
                            R.string.game_my_info_remain_hp, gameBattleState.myRemainHp
                        )
                    )
                    Text(
                        text = ctx.getString(
                            R.string.game_my_info_remain_turn,
                            RuleConst.CARD_BATTLE_MAX_ROUND - gameBattleState.roundCount
                        )
                    )
                    Text(
                        text = ctx.getString(
                            R.string.game_my_info_used_card, gameState.usedCardCount
                        )
                    )
                    Text(
                        text = ctx.getString(
                            R.string.game_my_info_used_cost, gameState.usedCardCost
                        ),
                        color = if (gameState.usedCardCost > RuleConst.CARD_BATTLE_MAX_COST) Color.Red else Color.Black

                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .height(180.dp)
                        .padding(end = 2.dp)
                        .background(Color.White)
                ) {
                    Text(
                        text = ctx.getString(R.string.game_battle_compare_text), fontSize = 40.sp
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(2f)
                        .height(180.dp)
                        .padding(end = 2.dp)
                        .background(Color.White)
                        .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = ctx.getString(
                            R.string.game_com_info
                        ),
                        fontSize = 20.sp,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                    )
                    Box(Modifier.height(6.dp))
                    Text(
                        text = ctx.getString(
                            R.string.card_detail_att, gameState.selectedGameStage?.id ?: 0
                        )
                    )
                    Text(
                        text = ctx.getString(
                            R.string.card_detail_def, gameState.selectedGameStage?.id ?: 0
                        )
                    )
                    Text(
                        text = ctx.getString(
                            R.string.card_detail_spd, gameState.selectedGameStage?.id ?: 0
                        )
                    )
                    Text(
                        text = ctx.getString(
                            R.string.card_detail_hp, gameBattleState.comRemainHp
                        )
                    )
                    Text(
                        text = ctx.getString(
                            R.string.card_detail_mp, gameState.selectedGameStage?.id ?: 0
                        )
                    )
                }
            }

            Column(Modifier.weight(1f)) {
                GameBattleScreen(
                    gameState, selectedCard, gameState.selectedGameStage, gameBattleViewModel
                ) {
                    gameViewModel.updateSelectedCardList(selectedCard)
                }
            }
            if (gameBattleViewModel.state.value.viewMode == GameBattleState.VIEW_MODE_DEFAULT) {
                GameSelectCardSlotScreen(selectedCard, selectedType, gameBattleState.roundCount == 0)

                if (gameBattleState.roundCount == 0) {
                    val selectedCardType = when (selectedType.value) {
                        0 -> {
                            ctx.getString(R.string.game_att)
                        }

                        1 -> {
                            ctx.getString(R.string.game_def)
                        }

                        2 -> {
                            ctx.getString(R.string.game_speed)
                        }

                        3 -> {
                            ctx.getString(R.string.game_hp)
                        }

                        else -> {
                            ctx.getString(R.string.game_mp)
                        }
                    }
                    gameViewModel.updateCardCost(selectedCard)

                    Text(text = "$selectedCardType ${ctx.getString(R.string.game_selected_card_guid)}")
                    Row {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                        ) {
                            items(gameState.cardList) { item ->

                                val selectedIndex = isSelectedCard(item, selectedCard)
                                val isSelected = selectedIndex > -1
//                            val isEnabled = isEnabledCard(gameState.selectedCardList, item)
                                CardListItemScreen(
                                    card = item,
                                    visibleInfoType = selectedType.value,
                                    height = 180f,
                                    isSelected,
                                    true
                                ) { card ->
                                    if (isSelected) {
                                        selectedCard[selectedIndex].value = null
                                    }
                                    selectedCard[selectedType.value].value = card
                                    gameBattleViewModel.updateMyRemainHp(selectedCard)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun isEnabledCard(cardList: List<Card?>, targetCard: Card): Boolean {
    cardList.forEach {
        it?.let { card ->
            if (card.id == targetCard.id) {
                return false
            }
        }
    }
    return true
}

fun isSelectedCard(card: Card, list: List<MutableState<Card?>>): Int {
    var result = -1
    repeat(list.size) {
        if (list[it].value?.id == card.id) {
            result = it
        }
    }
    return result
}