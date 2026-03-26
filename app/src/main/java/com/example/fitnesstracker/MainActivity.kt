package com.example.fitnesstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.ui.components.BottomNavBar
import com.example.fitnesstracker.ui.screens.*
import com.example.fitnesstracker.ui.theme.Background
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessTrackerTheme {
                KineticApp()
            }
        }
    }
}

@Composable
fun KineticApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "dashboard"

    val showBottomBar = currentRoute in setOf("dashboard", "train", "progress", "profile", "nutrition")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Background,
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            composable("dashboard") {
                DashboardScreen(navController)
            }
            composable("train") {
                WorkoutLibraryScreen(navController)
            }
            composable("workout_detail/{workoutId}") { backStack ->
                val workoutId = backStack.arguments?.getString("workoutId") ?: "1"
                WorkoutDetailScreen(navController, workoutId)
            }
            composable("activity_tracking") {
                ActivityTrackingScreen(navController)
            }
            composable("nutrition") {
                NutritionScreen(navController)
            }
            composable("progress") {
                ProgressScreen(navController)
            }
            composable("profile") {
                ProfileScreen(navController)
            }
        }
    }
}
