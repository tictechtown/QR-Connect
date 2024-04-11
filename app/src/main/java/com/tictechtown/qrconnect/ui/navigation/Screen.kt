package com.tictechtown.qrconnect.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList(),
) {
    data object Home : Screen("home")

    data object Detail : Screen(
        route = "detail/{qrId}",
        navArguments = listOf(navArgument("qrId") {
            type = NavType.LongType
        })
    ) {
        fun createRoute(id: Long) = "detail/${id}"
    }

}