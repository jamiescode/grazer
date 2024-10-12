package com.jamiescode.grazer.navigation

sealed class Destinations(
    val route: String,
) {
    data object Login : Destinations(Routes.LOGIN.value)

    data object UserList : Destinations(Routes.USERLIST.value)

    data object Nowhere : Destinations("")
}
