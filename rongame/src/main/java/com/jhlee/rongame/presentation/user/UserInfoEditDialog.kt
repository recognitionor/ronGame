package com.jhlee.rongame.presentation.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jhlee.rongame.R

@Composable
fun UserInfoEditDialog(onDismiss: (name: String) -> Unit) {
    val ctx = LocalContext.current
    val showDialog = remember { mutableStateOf(true) }
    val inputText = remember { mutableStateOf("") }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text(text = ctx.getString(R.string.user_info_edit_name_title)) },
            text = {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    TextField(
                        value = inputText.value,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,  // 원하는 배경색으로 변경
                            cursorColor = Color.DarkGray,  // 커서 색상 (선택 사항)
                            textColor = Color.Black  // 텍스트 색상 (선택 사항)
                        ),
                        onValueChange = { inputText.value = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog.value = false
                        val enteredText = inputText.value
                        onDismiss(enteredText)
                        // 추가로 수행할 작업이 있다면 여기에 추가합니다.
                    }, modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = null
        )
    }
}
