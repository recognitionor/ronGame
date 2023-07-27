package com.jhlee.rongame.presentation.etc

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@Composable
fun CalendarScreen(attendViewModel: AttendViewModel) {
    val year by remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    val month by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MONTH) + 1) }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = year.toString(),
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = month.toString(),
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Log.d("jhlee", "${attendViewModel.state.value.dateList}")
        Calendar(year, month, attendViewModel, attendViewModel.state.value.dateList)
    }
}

@Composable
fun Calendar(year: Int, month: Int, attendViewModel: AttendViewModel, dateList: List<String>) {
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, 1)

    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    val daysOfWeek = arrayOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")


    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Surface(
                color = Color.Yellow, modifier = Modifier.weight(1f)
            ) {
                Text(
                    fontSize = 12.sp,
                    text = dayOfWeek,
                    color = contentColorFor(Color.Gray),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    val weeks = (daysInMonth + startDayOfWeek - 2) / 7 + 1

    Column(modifier = Modifier.fillMaxWidth()) {
        for (week in 0 until weeks) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (dayOfWeek in 1..7) {
                    val dayOfMonth = week * 7 + dayOfWeek - startDayOfWeek + 1


                    if (dayOfMonth in 1..daysInMonth) {
                        val date = "${year}${String.format("%02d", month)}${
                            String.format(
                                "%02d", dayOfMonth
                            )
                        }"
                        Log.d("jhlee", "data : $date")
                        Log.d("jhlee", "data2 : $dateList")
                        Surface(
                            color = if (dateList.contains(date)) Color.Black else Color.White,
                            shape = CircleShape,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(1f)
                                .clip(CircleShape)
                                .clickable(onClick = {
                                    // Do something when a date is clicked
                                })
                                .padding(8.dp),
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(CenterVertically)
                                    .fillMaxWidth(),
                                text = dayOfMonth.toString(),
                                color = if (dateList.contains(date)) Color.Yellow else Color.Black,
                            )
                        }
                    } else {
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
