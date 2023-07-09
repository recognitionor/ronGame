package com.jhlee.rongame.presentation.card_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardListScreen(cardListViewModel: CardListViewModel) {
    val state: CardListState = cardListViewModel.state.value
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(state.cardList.size) { index ->

            val card = state.cardList[index]
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CardListItemScreen(card = card, height = 180f, cardListViewModel)
            }
        }
    }
}
