package com.jhlee.rongame

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.jhlee.rongame.common.constants.ExtraConst
import com.jhlee.rongame.presentation.game.basic.GameScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stageId = intent.getIntExtra(ExtraConst.EXTRA_SELECTED_STAGE_KEY, -1)
        setContent {
            GameScreen(stageId) {
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}