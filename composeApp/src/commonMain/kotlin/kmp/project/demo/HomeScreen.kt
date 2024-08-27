package kmp.project.demo

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class DemoAppScreen(val title: String) {
    Start(title = "start"),
    Treasure(title = "treasure")
}

@Composable
fun DemoAppBar(
    currentScreen: DemoAppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun DemoApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = DemoAppScreen.valueOf(
        backStackEntry?.destination?.route ?: DemoAppScreen.Start.name
    )
    Scaffold(
        topBar = {
            DemoAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = DemoAppScreen.Start.name
        ) {
            composable(route = DemoAppScreen.Start.name) {
                StartMenuScreen(
                    onNextButtonClicked = { navController.navigate(DemoAppScreen.Treasure.name) }
                )
            }
            composable(route = DemoAppScreen.Treasure.name) {
                TreasureApp()
            }
        }
    }
}