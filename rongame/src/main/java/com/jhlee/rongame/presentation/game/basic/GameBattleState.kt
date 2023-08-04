package com.jhlee.rongame.presentation.game.basic

data class GameBattleState(
    val viewMode: Int = VIEW_MODE_DEFAULT,
    val isLoadDone: Boolean = false,
    val roundCount: Int = 0,
    val compareType: Int = 0,
    val compareMyValue: String = "",
    val compareComValue: String = "",
    val myRemainHp: Int = 0,
    val comRemainHp: Int = 0,
    val content: String = "",
    val error: String = "",
    val progressValue: Float = 0f,
    val randomValue: Int = 0
) {
    companion object {
        const val VIEW_MODE_PROGRESS = -1
        const val VIEW_MODE_DEFAULT = 0
        const val VIEW_MODE_READY = 1
        const val VIEW_MODE_STATE_MSG = 2
        const val VIEW_MODE_ATT = 3
        const val VIEW_MODE_DEF = 4
        const val VIEW_MODE_RANDOM_ATT_RESULT = 5
        const val VIEW_MODE_RANDOM_DEF_RESULT = 6
        const val VIEW_MODE_GAME_WIN_RESULT = 7
        const val VIEW_MODE_GAME_LOSE_RESULT = 8
        const val VIEW_MODE_FINISH = 9
    }
}
