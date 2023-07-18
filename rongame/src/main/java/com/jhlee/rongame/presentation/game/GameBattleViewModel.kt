package com.jhlee.rongame.presentation.game

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.const.GameConst
import com.jhlee.rongame.domain.const.GameConst.Companion.GAME_DELAY
import com.jhlee.rongame.domain.const.GameConst.Companion.GAME_SELECTED_CARD_TYPE_SPD
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.domain.model.GameStage
import com.jhlee.rongame.domain.usecase.user.UpdateUserMoneyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameBattleViewModel @Inject constructor(private val updateUserMoneyUseCase: UpdateUserMoneyUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(GameBattleState())

    val state: State<GameBattleState> = _state


    fun initGameStage(selectedGameStage: GameStage) {
        val offset = 10
        _state.value = _state.value.copy(
            viewMode = GameBattleState.VIEW_MODE_DEFAULT,
            comRemainHp = selectedGameStage.id + offset
        )
    }

    fun setMyRemainHp(selectedType: List<MutableState<Card?>>) {
        _state.value = _state.value.copy(
            myRemainHp = selectedType[GameConst.GAME_SELECTED_CARD_TYPE_HP].value?.hp ?: 0
        )
    }

    fun rewardWin(reward: Int) {
        updateUserMoneyUseCase(reward).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(viewMode = GameBattleState.VIEW_MODE_FINISH)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(viewMode = GameBattleState.VIEW_MODE_FINISH)
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(viewMode = GameBattleState.VIEW_MODE_PROGRESS)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkLose(): Boolean {
        var result = false
        if (state.value.myRemainHp < 1) {
            result = true
        }
        if (state.value.roundCount >= 10) {
            result = true
        }
        return result
    }

    private fun checkWin(): Boolean {
        if (state.value.comRemainHp < 1) {
            return true
        }
        return false
    }

    private suspend fun defense(
        selectedCard: List<MutableState<Card?>>, selectedGameStage: GameStage
    ) {
        val comAtt = selectedGameStage.id
        val myDef = selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_DEF].value?.defense ?: 0
        val myMp = selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_MP].value?.mp ?: 0
        _state.value = _state.value.copy(
            viewMode = GameBattleState.VIEW_MODE_DEF
        )

        var count = 0
        var progress = 0f
        while (true) {

            _state.value = _state.value.copy(
                viewMode = GameBattleState.VIEW_MODE_DEF,
                progressValue = progress,
                randomValue = (progress * (myDef + 1)).toInt()
            )
            delay(5)
            count++

            progress = if (progress > 1f) {
                0.00f
            } else {
                progress.plus(0.01f)
            }
            if (count > 1000) {
                break
            }
        }

        val randomValue = if (myMp >= myDef) {
            myDef
        } else {
            Random.nextInt(myMp, myDef + 1)
        }
        var damage = comAtt - randomValue
        if (damage < 1) {
            damage = 0
        }

        _state.value = _state.value.copy(
            randomValue = randomValue,
            viewMode = GameBattleState.VIEW_MODE_DEF,
            progressValue = (randomValue.toFloat() / (myDef))
        )
        delay(GAME_DELAY)
        var remainHp = state.value.myRemainHp - damage
        if (remainHp < 1) {
            remainHp = 0
        }

        _state.value = _state.value.copy(
            viewMode = GameBattleState.VIEW_MODE_RANDOM_DEF_RESULT,
            myRemainHp = remainHp,
            content = "${state.value.myRemainHp} - $damage = $remainHp"
        )
        delay(GAME_DELAY)
    }

    private suspend fun attack(
        selectedCard: List<MutableState<Card?>>, selectedGameStage: GameStage
    ) {
        val myAtt = selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_ATT].value?.attack ?: 0
        val myMp = selectedCard[GameConst.GAME_SELECTED_CARD_TYPE_MP].value?.mp ?: 0
        _state.value = _state.value.copy(
            viewMode = GameBattleState.VIEW_MODE_ATT
        )
        var count = 0
        var progress = 0f
        while (true) {
            _state.value = _state.value.copy(
                viewMode = GameBattleState.VIEW_MODE_ATT,
                progressValue = progress,
                randomValue = (progress * (myAtt + 1)).toInt()
            )
            delay(5)
            count++

            progress = if (progress > 1f) {
                0.00f
            } else {
                progress.plus(0.01f)
            }
            if (count > 1000) {
                break
            }
        }
        val randomValue = if (myMp >= myAtt) {
            myAtt
        } else {
            Random.nextInt(myMp, myAtt + 1)
        }
        var damage = randomValue - selectedGameStage.id
        if (damage < 1) {
            damage = 0
        }
        _state.value = _state.value.copy(
            randomValue = randomValue,
            viewMode = GameBattleState.VIEW_MODE_ATT,
            progressValue = randomValue.toFloat() / (myAtt)
        )

        delay(GAME_DELAY)
        var remainHp = state.value.comRemainHp - damage
        if (remainHp < 1) {
            remainHp = 0
        }

        _state.value = _state.value.copy(
            viewMode = GameBattleState.VIEW_MODE_RANDOM_ATT_RESULT,
            comRemainHp = remainHp,
            content = "${state.value.comRemainHp} - $damage = $remainHp"
        )
        delay(GAME_DELAY)
    }

    private fun nextRound(selectedCard: List<MutableState<Card?>>, nextViewMode: Int) {
        selectedCard.forEach {
            it.value = null
        }
        _state.value = _state.value.copy(
            viewMode = nextViewMode
        )
    }

    fun startRound(
        selectedCard: List<MutableState<Card?>>,
        selectedGameStage: GameStage,
        roundStartCallback: () -> Unit,
    ) {
        MainScope().launch {

            withContext(Dispatchers.IO) {
                val mySpd = selectedCard[GAME_SELECTED_CARD_TYPE_SPD].value?.speed ?: 0
                val comValue = selectedGameStage.id
//                gameState = gameState.copy(usedCardCount = gameState.usedCardCount.plus(selectedCard.size))
                // 스피드를 비교
                roundStartCallback.invoke()
                _state.value = _state.value.copy(
                    roundCount = _state.value.roundCount.plus(1),
                    viewMode = GameBattleState.VIEW_MODE_READY,
                    compareType = GAME_SELECTED_CARD_TYPE_SPD,
                    compareMyValue = mySpd.toString(),
                    compareComValue = comValue.toString()
                )
                delay(GAME_DELAY)
                _state.value = _state.value.copy(
                    viewMode = GameBattleState.VIEW_MODE_STATE_MSG
                )

                // 스피드가 빠른 쪽 부터 선공
                if (state.value.compareMyValue > state.value.compareComValue) {
                    attack(selectedCard, selectedGameStage)
                    if (checkWin()) {
                        _state.value = _state.value.copy(
                            viewMode = GameBattleState.VIEW_MODE_GAME_WIN_RESULT
                        )
                    } else {
                        defense(selectedCard, selectedGameStage)
                        if (checkLose()) {
                            nextRound(selectedCard, GameBattleState.VIEW_MODE_GAME_LOSE_RESULT)
                        } else {
                            nextRound(selectedCard, GameBattleState.VIEW_MODE_DEFAULT)
                        }

                    }
                } else {
                    defense(selectedCard, selectedGameStage)
                    delay(GAME_DELAY)
                    if (checkLose()) {
                        nextRound(selectedCard, GameBattleState.VIEW_MODE_GAME_LOSE_RESULT)
                    } else {
                        attack(selectedCard, selectedGameStage)
                        if (checkWin()) {
                            nextRound(selectedCard, GameBattleState.VIEW_MODE_GAME_WIN_RESULT)
                        } else {
                            nextRound(selectedCard, GameBattleState.VIEW_MODE_DEFAULT)
                        }
                    }
                }
            }
        }
    }
}