package com.jhlee.rongame.presentation.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.jhlee.rongame.R
import com.jhlee.rongame.domain.model.Card

@Composable
fun CardDetailDialog(card: Card?, onDismiss: () -> Unit) {
    val ctx = LocalContext.current
    val showDialog = remember { mutableStateOf(true) }
    val inputText = remember { mutableStateOf("") }
    AlertDialog(onDismissRequest = { onDismiss() }, title = {
        Text(
            text = ctx.getString(R.string.card_detail_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }, text = {
        Row {
            Column(
                modifier = Modifier.padding(bottom = 26.dp)
            ) {
                Text(
                    text = ctx.getString(R.string.card_detail_att, card?.hero?.attack),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = ctx.getString(R.string.card_detail_def, card?.hero?.defense),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = ctx.getString(R.string.card_detail_spd, card?.hero?.speed),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = ctx.getString(R.string.card_detail_hp, card?.hero?.hp),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = ctx.getString(R.string.card_detail_mp, card?.hero?.mp),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }

    }, confirmButton = {

    }, properties = DialogProperties(
        dismissOnBackPress = true, dismissOnClickOutside = true
    ), modifier = Modifier
        .padding(16.dp)
        .background(Color.White)
    )
}
