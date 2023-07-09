package com.jhlee.rongame.presentation.splash

import com.jhlee.rongame.domain.model.Hero
import com.jhlee.rongame.domain.model.UserInfo

data class SplashState(
    val isLoading: Boolean = false,
    val heroList: List<Hero> = emptyList(),
    val userInfo: UserInfo? = null,
    val error: String = ""
)