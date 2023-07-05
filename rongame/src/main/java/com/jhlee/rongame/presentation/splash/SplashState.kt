package com.jhlee.rongame.presentation.splash

import com.jhlee.rongame.domain.model.Hero

data class SplashState(
    val isLoading: Boolean = false, val result: List<Hero> = emptyList(), val error: String = ""
)