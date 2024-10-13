package com.jamiescode.grazer.navigation

sealed class Destinations(
    val route: String,
) {
    data object Login : Destinations(Routes.LOGIN.value)

    data object Users : Destinations(Routes.USERS.value)

    data object Nowhere : Destinations("")
}
