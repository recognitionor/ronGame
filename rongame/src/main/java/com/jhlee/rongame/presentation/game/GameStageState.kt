package com.jhlee.rongame.presentation.game

import com.jhlee.rongame.R
import com.jhlee.rongame.domain.const.GameConst
import com.jhlee.rongame.domain.const.GameStageConst
import com.jhlee.rongame.domain.model.GameStage

data class GameStageState(
    val isLoading: Boolean = false,
    val isLoadDone: Boolean = false,
    val selectedGameStage: GameStage? = null,
    val gameList: List<GameStage> = emptyList(),
    val error: String = "",
    val progress: Int = 0
) {
    companion object {
        val GAME_STATE_TYPE_MAP: HashMap<Int, Int> = hashMapOf<Int, Int>().apply {
            this[GameStageConst.GAME_STAGE_STATUS_READY] = R.string.game_stage_state_ready
            this[GameStageConst.GAME_STAGE_STATUS_DONE] = R.string.game_stage_state_done
            this[GameStageConst.GAME_STAGE_STATUS_NOT_OPEN] = R.string.game_stage_state_not_open
        }
    }
}