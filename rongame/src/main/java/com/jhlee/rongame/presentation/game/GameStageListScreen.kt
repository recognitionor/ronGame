package com.jhlee.rongame.presentation.game

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.GameActivity
import com.jhlee.rongame.R
import com.jhlee.rongame.common.constants.ExtraConst.Companion.EXTRA_SELECTED_STAGE_KEY
import com.jhlee.rongame.domain.const.GameStageConst
import com.jhlee.rongame.domain.model.GameStage
import com.jhlee.rongame.presentation.user.UserInfoViewModel

@Composable
fun GameStageListScreen() {
    val ctx = LocalContext.current
    val gameListViewModel: GameListViewModel = hiltViewModel()
    val userInfoViewModel: UserInfoViewModel = hiltViewModel()
    val showInfoDialog = remember { mutableStateOf<GameStage?>(null) }
    val state = gameListViewModel.state.value

    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                gameListViewModel.getGameList()
            }
        })

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.gameList) { gameStage ->
                GameItemScreen(gameStage) {
                    when (gameStage.status) {
                        GameStageConst.GAME_STAGE_STATUS_READY -> {
                            showInfoDialog.value = gameStage
                            gameListViewModel.selectGameStage(gameStage)
                        }

                        GameStageConst.GAME_STAGE_STATUS_DONE -> {
                            Toast.makeText(
                                ctx,
                                ctx.getString(R.string.game_stage_state_done_message),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        GameStageConst.GAME_STAGE_STATUS_NOT_OPEN -> {
                            Toast.makeText(
                                ctx,
                                ctx.getString(R.string.game_stage_state_not_open_message),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (showInfoDialog.value != null) {
            AlertDialog(onDismissRequest = { }, title = {
                Text(
                    text = ctx.getString(
                        R.string.game_start_confirm_message, (showInfoDialog.value?.cost ?: 0)
                    ),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }, text = {}, confirmButton = {
                Button(
                    onClick = {
                        state.selectedGameStage?.let {
                            userInfoViewModel.updateUserInfoMoney(-it.cost)
                            showInfoDialog.value = null
                            val intent = Intent(ctx, GameActivity::class.java)
                            intent.putExtra(EXTRA_SELECTED_STAGE_KEY, it.id)
                            resultLauncher.launch(intent)
                        }
                    }, modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = ctx.getString(R.string.confirm))
                }
            }, dismissButton = {

                Button(
                    onClick = {
                        showInfoDialog.value = null
                    }, modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = ctx.getString(R.string.cancel))
                }
            })
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
