package com.example.composeapplication

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapplication.screen.main.MainScreen
import com.example.composeapplication.screen.settings.SettingsScreen
import com.example.composeapplication.ui.custom.ItisTheme

sealed class Screen(
    val route: String,
    @StringRes
    val name: Int,
    val icon: ImageVector,
) {
    object Settings : Screen(
        route = "settings",
        name = R.string.screen_settings,
        icon = Icons.Filled.Settings,
    )

    object Main : Screen(
        route = "main",
        name = R.string.screen_main,
        icon = Icons.Filled.Home
    )

    object Cart : Screen(
        route = "cart",
        name = R.string.screen_cart,
        icon = Icons.Filled.ShoppingCart
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItisNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Main
) {
    val items = listOf(
        Screen.Main,
        Screen.Cart,
        Screen.Settings,
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = ItisTheme.colors.tintColor
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.name)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = startDestination.route,
            Modifier.padding(innerPadding),
        ) {
            composable(Screen.Settings.route) { SettingsScreen(navController) }
            composable(Screen.Main.route) { MainScreen(navController) }
            composable(Screen.Cart.route) { MainScreen(navController) }
        }
    }
}