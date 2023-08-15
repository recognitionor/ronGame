package com.jhlee.rongame

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.jhlee.rongame.presentation.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colors = lightColors(primary = Color.Blue), Typography(
                    defaultFontFamily = FontFamily(Font(R.font.yeongdeok_blueroad))
                )
            ) {


                SplashScreen {
                    finish()
                }
            }

        }

    }
}