package com.jhlee.rongame.presentation.splash

import com.jhlee.rongame.domain.model.UserInfo

data class SplashState(
    val isLoading: Boolean = false,
    val userInfo: UserInfo? = null,
    val error: String = ""
)