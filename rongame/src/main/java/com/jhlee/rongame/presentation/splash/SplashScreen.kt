package com.jhlee.rongame.presentation.splash

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.MainActivity
import com.jhlee.rongame.R
import com.jhlee.rongame.common.constants.HeroConst
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.presentation.user.UserInfoEditDialog

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
                if (state.userInfo == null) {
                    UserInfoEditDialog { name ->
                        viewModel.insertUserInfo(UserInfo(1, name, 1000))
                    }
                } else {
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