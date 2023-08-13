package com.jhlee.rongame.presentation.card_list

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.jhlee.rongame.domain.const.GameConst
import com.jhlee.rongame.domain.model.Card
import com.jhlee.rongame.presentation.card.CardDetailDialog
import com.jhlee.rongame.presentation.common.StarRatingBar

@Composable
fun CardListItemScreen(
    card: Card,
    visibleInfoType: Int = -1,
    height: Float,
    selected: Boolean = false,
    isEnabled: Boolean = true,
    onItemClick: ((card: Card) -> Unit)? = null
) {
    val ctx = LocalContext.current
    val showInfoDialog = remember { mutableStateOf(false) }
    val cardWidth = (height * 0.8)
    val cardImg: String = card.image

    val powerStr: String = GameUtils.getPower(card).toString()
    val costStr: String = card.cost.toString()

    val nameStr: String = card.name
    val gradeStr: String = (card.grade + 1).toString()
    val color: Color = GradeConst.TYPE_MAP[card.grade]!!.color
    val textColor: Color = Color.Black

    Card(modifier = Modifier
        .run {
            size(width = cardWidth.dp, height = height.dp)
                .padding(4.dp)
                .border(width = 2.dp, color = color, shape = RoundedCornerShape(8.dp))
        }
        .clickable {
            if (onItemClick == null) {
                showInfoDialog.value = true
            } else {
                if (isEnabled) {
                    onItemClick(card)
                }

            }
        }) {
        Box(
            modifier = Modifier.padding(5.dp)
        ) {


            // 패딩을 적용할 Box 컴포넌트 추가
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    if (selected && isEnabled) {
                        Image(
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.TopEnd),
                            painter = painterResource(id = R.drawable.ic_done),
                            contentDescription = "Selected",
                            colorFilter = ColorFilter.tint(color)
                        )
                    }

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
                                fontSize = 8.sp,
                                color = textColor
                            )
                            Text(
                                text = "${ctx.getString(R.string.card_gatcha_power_title)} $powerStr",
                                textAlign = TextAlign.Right,
                                fontSize = 8.sp,
                                color = textColor
                            )
                            Text(
                                text = "${ctx.getString(R.string.card_gatcha_cost_title)} $costStr",
                                textAlign = TextAlign.Right,
                                fontSize = 8.sp,
                                color = textColor
                            )

                            when (visibleInfoType) {
                                GameConst.GAME_SELECTED_CARD_TYPE_ATT -> {
                                    Text(
                                        text = ctx.getString(
                                            R.string.card_detail_att, card.attack
                                        ),
                                        textAlign = TextAlign.Right,
                                        fontSize = 8.sp,
                                        color = textColor
                                    )
                                }

                                GameConst.GAME_SELECTED_CARD_TYPE_DEF -> {
                                    Text(
                                        text = ctx.getString(
                                            R.string.card_detail_def, card.defense
                                        ),
                                        textAlign = TextAlign.Right,
                                        fontSize = 8.sp,
                                        color = textColor
                                    )
                                }

                                GameConst.GAME_SELECTED_CARD_TYPE_SPD -> {
                                    Text(
                                        text = ctx.getString(
                                            R.string.card_detail_spd, card.speed
                                        ),
                                        textAlign = TextAlign.Right,
                                        fontSize = 8.sp,
                                        color = textColor
                                    )
                                }

                                GameConst.GAME_SELECTED_CARD_TYPE_HP -> {
                                    Text(
                                        text = ctx.getString(
                                            R.string.card_detail_hp, card.hp
                                        ),
                                        textAlign = TextAlign.Right,
                                        fontSize = 8.sp,
                                        color = textColor
                                    )
                                }

                                GameConst.GAME_SELECTED_CARD_TYPE_MP -> {
                                    Text(
                                        text = ctx.getString(
                                            R.string.card_detail_mp, card.mp
                                        ),
                                        textAlign = TextAlign.Right,
                                        fontSize = 8.sp,
                                        color = textColor
                                    )
                                }
                            }
                        }

                        Image(
                            painter = painterResource(id = R.drawable.ic_info),
                            contentDescription = "",
                            modifier = Modifier
                                .size(
                                    (cardWidth * 0.135).dp, (height * 0.135).dp
                                )
                                .clickable {
                                    showInfoDialog.value = true
                                },
                            colorFilter = ColorFilter.tint(color)
                        )
                    }
                }
                if (isEnabled) {
                    Image(
                        painter = painterResource(id = cardImg.toInt()),
                        contentDescription = "",
                        modifier = Modifier
                            .size(
                                (cardWidth * 0.4).dp, (height * 0.4).dp
                            )
                            .background(Color.White)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "",
                        modifier = Modifier
                            .size(
                                (cardWidth * 0.4).dp, (height * 0.4).dp
                            )
                            .background(Color.White),
                        colorFilter = ColorFilter.tint(color)
                    )
                }

                Text(text = nameStr, textAlign = TextAlign.Center)
                StarRatingBar(((card.grade.plus(1)) ?: 0), color, 12.dp)
            }
        }
    }
    // 다이얼로그 표시
    if (showInfoDialog.value) {
        CardDetailDialog(card) {
            showInfoDialog.value = false
        }
    }

}
