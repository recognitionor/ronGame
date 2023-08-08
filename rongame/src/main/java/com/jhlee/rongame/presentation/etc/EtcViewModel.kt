package com.jhlee.rongame.presentation.etc

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhlee.rongame.common.Resource
import com.jhlee.rongame.domain.usecase.attend.CreateAttendUseCase
import com.jhlee.rongame.domain.usecase.attend.GetAttendUseCase
import com.jhlee.rongame.domain.usecase.quiz.GetQuizListUseCase
import com.jhlee.rongame.domain.usecase.quiz.InsertQuizListUseCase
import com.jhlee.rongame.domain.usecase.user.UpdateUserMoneyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EtcViewModel @Inject constructor(
    private val createAttendUseCase: CreateAttendUseCase,
    private val updateUserMoneyUseCase: UpdateUserMoneyUseCase,
    private val getAttendUseCase: GetAttendUseCase,
    private val createQuizListUseCase: InsertQuizListUseCase,
    private val getQuizListUseCase: GetQuizListUseCase
) : ViewModel() {
    private val _state = mutableStateOf(AttendState())

    val state: State<AttendState> = _state

    init {
        getAttendList()
        getQuizList()
    }

    private fun insertQuizList() {
        createQuizListUseCase().onEach { }.launchIn(viewModelScope)
    }

    private fun getQuizList() {
        getQuizListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {

                    result.data?.let {
                        Log.d("jhlee", "test  : $it")
                        _state.value = _state.value.copy(quizList = it)
                    }
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun createAttend(date: Long) {
        createAttendUseCase(date).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    updateUserMoneyUseCase(+300).launchIn(viewModelScope)
                    getAttendList()
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun rewardUpdate(reward: Int) {
        updateUserMoneyUseCase(reward).launchIn(viewModelScope)
    }

    private fun getAttendList() {
        getAttendUseCase().onEach { result ->
            result.data?.let { date ->
                _state.value = _state.value.copy(dateList = date)
            }
        }.launchIn(viewModelScope)
    }

}