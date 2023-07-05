package com.jhlee.rongame.presentation.splash

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.MainActivity
import com.jhlee.rongame.common.constants.HeroConst

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel(), onFinish: () -> Unit) {
    val ctx = LocalContext.current
    val state = viewModel.state.value
    Column {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Text(
//                    text = ctx.getString(R.string.title),
//                    fontSize = 40.sp,
//                    textAlign = TextAlign.Center
//                )
                if (state.result.isNotEmpty()) {
                    HeroConst.HERO_LIST = state.result
                    onFinish.invoke()
                    ctx.startActivity(Intent(ctx, MainActivity::class.java))

                }
                if (state.isLoading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}