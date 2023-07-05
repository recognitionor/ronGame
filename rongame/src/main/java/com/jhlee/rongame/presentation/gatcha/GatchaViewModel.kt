package com.jhlee.rongame.presentation.gatcha

import androidx.lifecycle.ViewModel
import com.jhlee.rongame.domain.usecase.card.CreateCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GatchaViewModel @Inject constructor(private val getCardUseCase: CreateCardUseCase) :
    ViewModel() {


}