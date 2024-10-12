package com.jamiescode.grazer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jamiescode.grazer.navigation.AppNavigator
import com.jamiescode.grazer.navigation.Destinations
import com.jamiescode.grazer.presentation.composable.grazerTopBar
import com.jamiescode.grazer.presentation.screen.login.loginScreen
import com.jamiescode.grazer.presentation.screen.userlist.userListScreen
import com.jamiescode.grazer.theme.grazerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Navigation setup
            val navController = rememberNavController()
            val navigationEvents =
                appNavigator.navigationEventsLiveData.asFlow().collectAsState(
                    initial = Destinations.Nowhere,
                )
            handleNavigationEvents(navigationEvents, navController)

            // Jetpack Compose
            grazerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { grazerTopBar() },
                ) { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = Destinations.Login.route,
                            enterTransition = { fadeIn(animationSpec = tween(durationMillis = 0)) },
                            exitTransition = { fadeOut(animationSpec = tween(durationMillis = 0)) },
                            builder = { createNavigationRoutes() },
                        )
                    }
                }
            }
        }
    }

    private fun handleNavigationEvents(
        navigationEvents: State<Destinations>,
        navController: NavController,
    ) {
        when (val destination = navigationEvents.value) {
            Destinations.Nowhere -> {} // Do nothing
            else -> {
                navController.navigate(destination.route)
            }
        }
    }

    private fun NavGraphBuilder.createNavigationRoutes() {
        composable(
            route = Destinations.Login.route,
        ) {
            loginScreen()
        }
        composable(
            route = Destinations.UserList.route,
        ) {
            userListScreen()
        }
    }
}
