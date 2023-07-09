package com.jhlee.rongame.presentation.card

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.usecase.card.CreateCardUseCase
import com.jhlee.rongame.domain.usecase.user.GetUserInfoUseCase
import com.jhlee.rongame.domain.usecase.user.UpdateUserMoneyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val createCardUseCase: CreateCardUseCase,
    private val updateUserInfoUseCase: UpdateUserMoneyUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CardState())

    val state: State<CardState> = _state

    // 다이얼로그 표시를 위한 상태 변수
    private val _showInfoDialog = mutableStateOf(false)
    val showInfoDialog: State<Boolean> = _showInfoDialog

    // 다이얼로그 표시 상태 변경 함수
    fun setShowInfoDialog(value: Boolean) {
        _showInfoDialog.value = value
    }

    // 다이얼로그 표시 관련 로직
    fun onInfoIconClicked() {
        // 다이얼로그 표시 상태 변경
        setShowInfoDialog(true)
    }

    // 다이얼로그 닫기 관련 로직
    fun onInfoDialogDismissed() {
        // 다이얼로그 표시 상태 변경
        setShowInfoDialog(false)
    }

    private fun refreshUserInfo() {
        Log.d("jhlee", "refreshUserInfo")
        getUserInfoUseCase().onEach { }.launchIn(viewModelScope)
    }

    private fun updateUserInfo() {
        updateUserInfoUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    refreshUserInfo()
                }

                is Resource.Error -> {
                }

                is Resource.Loading -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setFlagCardStateLoadDone() {
        _state.value = _state.value.copy(isLoadDone = true)
    }

    fun gatchaCard() {
        if (_state.value.isLoading) {
            return
        }

        createCardUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data != null) {
                        _state.value =
                            CardState(isLoading = false, isLoadDone = false, card = result.data)
                        updateUserInfo()
                    } else {
                        _state.value = CardState(
                            isLoading = false,
                            isLoadDone = false,
                            error = result.message ?: "card is null"
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.value = CardState(
                        isLoading = true,
                        isLoadDone = false,
                        progress = _state.value.progress.plus(1)
                    )
                }

                is Resource.Error -> {
                    _state.value = CardState(
                        isLoading = false,
                        isLoadDone = false,
                        error = result.message ?: "user info error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}