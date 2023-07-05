package com.jhlee.rongame.presentation.card

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhlee.rongame.R
import com.jhlee.rongame.common.Utils
import com.jhlee.rongame.common.constants.GradeConst
import com.jhlee.rongame.presentation.common.StarRatingBar

@Composable
fun CardScreen(viewModel: CardViewModel = hiltViewModel(), height: Float) {
    val ctx = LocalContext.current
    val cardWidth = (height * 0.8)
    val state: CardState = viewModel.state.value
    var color: Color = GradeConst.TYPE_MAP[0]!!.color
    var powerStr = "?"
    var costStr = "?"
    var nameStr = "???"
    var gradeStr = "?"
    var textColor = Color.Black
    var cardImg: String = R.drawable.ic_contact_support.toString()

    if (state.card != null) {
        powerStr = Utils.getPower(state.card).toString()
        costStr = state.card.cost.toString()
        cardImg = state.card.hero.image
        nameStr = state.card.name
        gradeStr = (state.card.grade + 1).toString()
        color = GradeConst.TYPE_MAP[state.card.grade]!!.color
        textColor = Color.Black
        Log.d("jhlee", "color : ${state.card}")
    }
    if (state.isLoading) {
        val index = (state.progress % GradeConst.TYPE_MAP.size)
        color = GradeConst.TYPE_MAP[index]!!.color
        textColor = color
    }

    Card(
        modifier = Modifier.run {
            size(width = cardWidth.dp, height = height.dp)
                .padding(10.dp)
                .border(width = 4.dp, color = color, shape = RoundedCornerShape(8.dp))
        },
    ) {
        Box(
            modifier = Modifier.padding(18.dp)
        ) { // 패딩을 적용할 Box 컴포넌트 추가
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Column {
                        Text(
                            text = "${ctx.getString(R.string.card_gatcha_grade_title)} $gradeStr",
                            textAlign = TextAlign.Right,
                            fontSize = 14.sp,
                            color = textColor
                        )
                        Text(
                            text = "${ctx.getString(R.string.card_gatcha_power_title)} $powerStr",
                            textAlign = TextAlign.Right,
                            fontSize = 14.sp,
                            color = textColor
                        )
                        Text(
                            text = "${ctx.getString(R.string.card_gatcha_cost_title)} $costStr",
                            textAlign = TextAlign.Right,
                            fontSize = 14.sp,
                            color = textColor
                        )
                    }
                }
                val paint = painterResource(id = cardImg.toInt())
                Image(
                    painter = paint,
                    contentDescription = "",
                    Modifier.size((cardWidth * 0.4).dp, (height * 0.4).dp),
                    colorFilter = if (state.isLoading) ColorFilter.tint(color) else null

                )
                Text(text = nameStr, textAlign = TextAlign.Center)
                StarRatingBar(((state.card?.grade?.plus(1)) ?: 0), color)

            }
        }
    }
}
