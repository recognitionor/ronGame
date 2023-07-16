package com.jhlee.rongame.presentation.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jhlee.rongame.R
import com.jhlee.rongame.domain.const.GameConst
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.GameStage

@Composable
fun GameBattleScreen(
    selectedCard: List<MutableState<Card?>>,
    selectedGameStage: GameStage?,
    gameBattleViewModel: GameBattleViewModel
) {
    val ctx = LocalContext.current
    val state = gameBattleViewModel.state.value
    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state.viewMode) {
            GameBattleState.VIEW_MODE_DEFAULT -> {
                Text(text = "현재 라운드 : ${state.roundCount + 1}")
                Button(onClick = {
                    selectedGameStage?.let {
                        gameBattleViewModel.startRound(selectedCard, it)
                    }
                }) {
                    Text(text = "시작")
                }
            }

            GameBattleState.VIEW_MODE_READY -> {
                var msg = ""
                Column {
                    Text(text = "state.compareType")
                    Text(text = "${state.compareMyValue} ${ctx.getString(R.string.game_battle_compare_text)} ${state.compareComValue}")
                }
            }

            GameBattleState.VIEW_MODE_STATE_MSG -> {
                Column {
                    val msg =
                        if (state.compareMyValue > state.compareComValue) ctx.getString(R.string.game_battle_first_me) else ctx.getString(
                            R.string.game_battle_first_com
                        )
                    Text(text = msg)
                }
            }

            GameBattleState.VIEW_MODE_ATT -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                ) {
                    Text(
                        text = ctx.getString(
                            R.string.game_battle_my_att,
                            selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_ATT].value?.attack ?: 0
                        )
                    )
                    Text(
                        text = ctx.getString(
                            R.string.game_battle_com_def, selectedGameStage?.id ?: 0
                        )
                    )
                    Card(modifier = Modifier.padding(8.dp)) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            progress = state.progressValue,
                            color = Color.Red
                        )

                        Text(text = state.randomValue.toString())
                    }

                    Row {
                        Text(
                            text = ctx.getString(
                                R.string.game_progress_min_attack,
                                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_MP].value?.mp ?: 0
                            )
                        )
                        Box(Modifier.weight(1f))
                        val maxAtt =
                            (selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_ATT].value?.attack
                                ?: 0) - (selectedGameStage?.id ?: 0)
                        Text(
                            text = ctx.getString(
                                R.string.game_progress_max_attack, maxAtt
                            )
                        )
                    }
                }
            }

            GameBattleState.VIEW_MODE_DEF -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                ) {
                    Text(
                        text = ctx.getString(
                            R.string.game_battle_my_def,
                            selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_DEF].value?.defense ?: 0
                        )
                    )
                    Text(
                        text = ctx.getString(
                            R.string.game_battle_com_att, selectedGameStage?.id ?: 0
                        )
                    )
                    Card(modifier = Modifier.padding(8.dp)) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            progress = state.progressValue,
                            color = Color.Blue
                        )

                        Text(text = state.randomValue.toString())
                    }

                    Row {
                        Text(
                            text = ctx.getString(
                                R.string.game_progress_min_defense,
                                selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_MP].value?.mp ?: 0
                            )
                        )
                        Box(Modifier.weight(1f))
                        val maxAtt =
                            (selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_DEF].value?.defense
                                ?: 0)
                        Text(
                            text = ctx.getString(
                                R.string.game_progress_max_defense, maxAtt
                            )
                        )
                    }
                }
            }

            GameBattleState.VIEW_MODE_GAME_LOSE_RESULT -> {
                Text(text = "패")
            }

            GameBattleState.VIEW_MODE_GAME_WIN_RESULT -> {
                Text(text = "승")
            }
            GameBattleState.VIEW_MODE_RANDOM_DEF_RESULT -> {
                Text(
                    text = ctx.getString(
                        R.string.game_battle_defense_result, state.myRemainHp, state.content
                    )
                )
            }
            GameBattleState.VIEW_MODE_RANDOM_ATT_RESULT -> {
                Text(
                    text = ctx.getString(
                        R.string.game_battle_attack_result, state.comRemainHp, state.content
                    )
                )
            }
        }
    }
}