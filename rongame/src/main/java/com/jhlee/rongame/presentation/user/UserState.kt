package com.jhlee.rongame.presentation.user

import com.jhlee.rongame.domain.model.UserInfo


data class UserState(
    val isLoading: Boolean = false, var user: UserInfo? = null, val error: String = ""
)
