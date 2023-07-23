package com.jhlee.rongame.presentation.game

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jhlee.rongame.R
import com.jhlee.rongame.domain.const.GameStageConst
import com.jhlee.rongame.domain.model.GameStage
import com.jhlee.rongame.presentation.game.GameStageState.Companion.GAME_STATE_TYPE_MAP

@Composable
fun GameItemScreen(
    gameStage: GameStage, onItemClick: (GameStage) -> Unit
) {
    val ctx = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable { onItemClick(gameStage) }
            .background(MaterialTheme.colors.surface)
            .padding(16.dp)
            .fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = ctx.getString(R.string.game_list_item_level, gameStage.id),
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = ctx.getString(R.string.game_list_item_cost, gameStage.cost),
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = ctx.getString(R.string.game_list_item_reward, gameStage.reward),
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = MaterialTheme.colors.onSurface
                )
            }
            val status = GAME_STATE_TYPE_MAP[gameStage.status]?.let { ctx.getString(it) } ?: ""
            val statusColor = when (gameStage.status) {
                GameStageConst.GAME_STAGE_STATUS_READY -> {
                    Color.Red
                }

                GameStageConst.GAME_STAGE_STATUS_DONE -> {
                    Color.Green
                }

                else -> {
                    Color.Magenta
                }
            }
            Text(
                text = status,
                color = statusColor,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )
    }
}
