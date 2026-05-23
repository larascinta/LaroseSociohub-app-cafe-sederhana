package com.example.larosesociohub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.larosesociohub.ui.*
import com.example.larosesociohub.ui.theme.LaroseSociohubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaroseSociohubTheme {
                val navController = rememberNavController()
                
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "splash", 
                        modifier = Modifier.padding(innerPadding),
                        enterTransition = {
                            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
                        },
                        exitTransition = {
                            slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
                        },
                        popEnterTransition = {
                            slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
                        },
                        popExitTransition = {
                            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
                        }
                    ) {
                        composable("splash") {
                            SplashScreen(onNextScreen = {
                                navController.navigate("home") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            })
                        }
                        
                        composable(route = "home") { _: NavBackStackEntry ->
                            HomeScreen(
                                onNavigateToMenu = { navController.navigate("menu") },
                                onNavigateToProfile = { navController.navigate("profile") }
                            )
                        }
                        
                        composable(route = "menu") { _: NavBackStackEntry ->
                            MenuScreen(
                                onNavigateToDetail = { menuId ->
                                    navController.navigate("menu/$menuId")
                                },
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        
                        composable(
                            route = "menu/{menuId}",
                            arguments = listOf(navArgument("menuId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val menuId = backStackEntry.arguments?.getInt("menuId") ?: 0
                            DetailMenuScreen(
                                menuId = menuId,
                                onBack = { navController.popBackStack() }
                            )
                        }
                        
                        composable(route = "profile") { _: NavBackStackEntry ->
                            ProfileScreen(
                                onNavigateToEdit = { navController.navigate("editProfile") },
                                onBack = { navController.popBackStack() }
                            )
                        }

                        composable(route = "editProfile") { _: NavBackStackEntry ->
                            EditProfileScreen(
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
