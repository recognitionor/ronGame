package com.jhlee.rongame.presentation.gatcha

import com.jhlee.rongame.domain.model.UserInfo

data class GatchaState(
    val isLoading: Boolean = false, val user: UserInfo? = null, val error: String = ""
)