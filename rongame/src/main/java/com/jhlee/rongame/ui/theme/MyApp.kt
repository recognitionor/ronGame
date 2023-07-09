package com.jhlee.rongame.ui.theme

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jhlee.rongame.presentation.game.GameScreen
import com.jhlee.rongame.presentation.home.HomeScreen
import com.jhlee.rongame.presentation.ProfileScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigationBar(navController)
    }) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen()
            }
            composable("search") {
                GameScreen()
            }
            composable("profile") {
                ProfileScreen()
            }
        }
    }
}
