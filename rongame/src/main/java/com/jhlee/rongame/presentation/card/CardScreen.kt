package com.jhlee.rongame.presentation.card

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.jhlee.rongame.R
import com.jhlee.rongame.common.utils.GameUtils
import com.jhlee.rongame.common.constants.GradeConst
import com.jhlee.rongame.presentation.common.StarRatingBar
import com.jhlee.rongame.presentation.user.UserInfoViewModel
import com.jhlee.rongame.presentation.user.UserState

@Composable
fun CardScreen(
    cardViewModel: CardViewModel, userInfoViewModel: UserInfoViewModel, height: Float
) {
    val ctx = LocalContext.current
    val cardWidth = (height * 0.8)
    val userStateValue: UserState = userInfoViewModel.state.value
    val cardStateValue: CardState = cardViewModel.state.value
    val isLoading = cardStateValue.isLoading
    var color: Color = GradeConst.TYPE_MAP[0]!!.color
    var powerStr = "?"
    var costStr = "?"
    var nameStr = "???"
    var gradeStr = "?"
    var textColor = Color.Black
    var cardImg: String = R.drawable.ic_contact_support.toString()
    val showInfoDialog = cardViewModel.showInfoDialog.value


    if (cardStateValue.card != null) {
        if (!cardStateValue.isLoadDone) {
            Toast.makeText(ctx, ctx.getString(R.string.card_gatcha_done), Toast.LENGTH_SHORT).show()
            userInfoViewModel.getUser()
        }
        powerStr = GameUtils.getPower(cardStateValue.card).toString()
        costStr = cardStateValue.card.cost.toString()
        cardImg = cardStateValue.card.image
        nameStr = cardStateValue.card.name
        gradeStr = (cardStateValue.card.grade + 1).toString()
        color = GradeConst.TYPE_MAP[cardStateValue.card.grade]!!.color
        textColor = Color.Black
        cardViewModel.setFlagCardStateLoadDone()
    }

    if (isLoading) {
        val index = (cardStateValue.progress % GradeConst.TYPE_MAP.size)
        color = GradeConst.TYPE_MAP[index]!!.color
        textColor = color
    }

    Card(modifier = Modifier.run {
        size(width = cardWidth.dp, height = height.dp)
            .padding(10.dp)
            .border(width = 4.dp, color = color, shape = RoundedCornerShape(8.dp))
            .clickable {
                if ((userStateValue.user?.money ?: 0) > 0) {
                    cardViewModel.gatchaCard()
                } else {
                    Toast
                        .makeText(
                            ctx, ctx.getString(R.string.card_gatcha_no_money), Toast.LENGTH_SHORT
                        )
                        .show()
                }
            }
    }) {
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
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
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

                        Image(
                            painter = painterResource(id = R.drawable.ic_info),
                            contentDescription = "",
                            modifier = Modifier
                                .size(
                                    (cardWidth * 0.135).dp, (height * 0.135).dp
                                )
                                .clickable(cardStateValue.card != null) {
                                    cardViewModel.onInfoIconClicked()
                                },
                            colorFilter = if (isLoading) ColorFilter.tint(color) else null
                        )
                    }


                }
                Image(
                    painter = painterResource(id = cardImg.toInt()),
                    contentDescription = "",
                    modifier = Modifier
                        .size(
                            (cardWidth * 0.4).dp, (height * 0.4).dp
                        )
                        .background(Color.White),
                    colorFilter = if (isLoading) ColorFilter.tint(color) else null
                )
                Text(text = nameStr, textAlign = TextAlign.Center)
                StarRatingBar(((cardStateValue.card?.grade?.plus(1)) ?: 0), color)
            }
        }
    }
    // 다이얼로그 표시
    if (showInfoDialog) {

        cardStateValue.card?.let {
            CardDetailDialog(
                it
            ) { cardViewModel.onInfoDialogDismissed() }
        } // 다이얼로그 닫기 함수 호출

    }
}
