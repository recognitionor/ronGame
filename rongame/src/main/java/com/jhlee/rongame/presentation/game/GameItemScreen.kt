package com.jhlee.rongame.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.domain.const.GameStageConst
import com.jhlee.rongame.domain.model.GameStage

@Composable
fun GameItemScreen(
    gameStage: GameStage, onItemClick: (GameStage) -> Unit
) {
    val ctx = LocalContext.current

    Column(
        modifier = Modifier.padding(start = 12.dp, end = 12.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(gameStage) }
            .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "${(gameStage.id)}",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = if (gameStage.status == GameStageConst.GAME_STAGE_STATUS_READY) "active" else "inactive",
                color = if (gameStage.status == GameStageConst.GAME_STAGE_STATUS_READY) Color.Green else Color.Red,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}
