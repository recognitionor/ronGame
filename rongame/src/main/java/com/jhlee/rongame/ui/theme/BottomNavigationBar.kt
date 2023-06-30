package com.jhlee.rongame.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.jhlee.rongame.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val items = listOf(
        BottomNavigationItem("Home", painterResource(R.drawable.ic_home_black_24dp), "home"),
        BottomNavigationItem(
            "Search", painterResource(R.drawable.ic_dashboard_black_24dp), "search"
        ),
        BottomNavigationItem(
            "Profile", painterResource(R.drawable.ic_notifications_black_24dp), "profile"
        )
    )
    val selectedItem = remember { mutableStateOf(items[0]) }

    Box() {
        BottomNavigation(
            backgroundColor = Color.White, contentColor = Color.Black
        ) {
            items.forEach { item ->
                BottomNavigationItem(icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = item == selectedItem.value,
                    onClick = {
                        selectedItem.value = item
                        navController.navigate(item.route)
                    })
            }
        }
    }
}

data class BottomNavigationItem(
    val label: String, val icon: Painter, val route: String
)

fun getIconResourceId(screen: String): Int {
    // 각 프래그먼트에 해당하는 아이콘 리소스 ID를 반환합니다.
    return when (screen) {
        "home" -> R.drawable.ic_home_black_24dp
        "search" -> R.drawable.ic_dashboard_black_24dp
        else -> R.drawable.ic_notifications_black_24dp
    }
}

fun getLabel(screen: String): String {
    // 각 프래그먼트에 해당하는 라벨을 반환합니다.
    return when (screen) {
        "home" -> "Home"
        "search" -> "Search"
        "profile" -> "Profile"
        else -> "Unknown"
    }
}
