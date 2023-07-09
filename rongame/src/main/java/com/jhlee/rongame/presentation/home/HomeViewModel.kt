package com.jhlee.rongame.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.domain.model.UserInfo
import com.jhlee.rongame.domain.usecase.user.GetUserInfoUseCase
import com.jhlee.rongame.domain.usecase.user.InsertUserInfoUseCase
import com.jhlee.rongame.domain.usecase.user.UpdateUserMoneyUseCase
import com.jhlee.rongame.presentation.card.CardState
import com.jhlee.rongame.presentation.user.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val insertUserInfoUseCase: InsertUserInfoUseCase,
    private val updateUserMoneyUseCase: UpdateUserMoneyUseCase
) : ViewModel() {
    private val _userState = mutableStateOf(UserState())

    val userState: State<UserState> = _userState

    private val _cardState = mutableStateOf(CardState())

    val cardState: State<CardState> = _cardState


    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase().collect { result ->
//                if (result.isSuccess) {
//                    _userInfoState.value = result.data
//                }
            }
        }
    }

    fun updateUserMoney(amount: Int) {
//        val updatedUserInfo = userInfoState?.copy(money = userInfoState.money - amount)
//        if (updatedUserInfo != null) {
//            viewModelScope.launch {
//                updateUserMoneyUseCase(updatedUserInfo)
//            }
//        }
    }
}
