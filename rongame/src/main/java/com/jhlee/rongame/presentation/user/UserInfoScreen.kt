package com.jhlee.rongame.presentation.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jhlee.rongame.R

@Composable
fun UserInfoScreen(viewModel: UserInfoViewModel) {

    val ctx = LocalContext.current
    val state = viewModel.state.value
    Column(modifier = Modifier.padding(20.dp)) {
        Row {
            state.user?.let { user ->
                Text(text = ctx.getString(R.string.user_info_name_title, user.name))
            }
        }
        Row {
            Text(text = ctx.getString(R.string.user_info_money_title))
            state.user?.let { user ->
                Text(text = ctx.getString(R.string.money_unit, user.money))
            }
        }
        if (state.error.isNotEmpty()) {

        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }


}