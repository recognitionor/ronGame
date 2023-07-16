package com.jhlee.rongame

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.common.constants.ExtraConst
import com.jhlee.rongame.presentation.game.GameListViewModel
import com.jhlee.rongame.presentation.game.GameScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stageId = intent.getIntExtra(ExtraConst.EXTRA_SELECTED_STAGE_KEY, -1)
        setContent {
            GameScreen(stageId)
        }
    }
}