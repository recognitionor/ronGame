package com.jhlee.rongame.presentation.user

import com.jhlee.rongame.domain.model.UserInfo


data class UserState(
    val isLoading: Boolean = false, val user: UserInfo? = null, val error: String = ""
)
