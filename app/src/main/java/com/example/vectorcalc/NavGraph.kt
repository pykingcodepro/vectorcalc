package com.example.vectorcalc

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun SetUpNavGraph( navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            HomeScreen(navController)
//            HomeScreen()
        }
        composable(
            route = Screen.ReportScreen.route + "/{vector1x}/{vector1y}/{vector1z}/{vector2x}/{vector2y}/{vector2z}",
            arguments = listOfNotNull(
                navArgument("vector1x") {
                    type = NavType.FloatType
                    defaultValue = 1f
                    nullable = false
                },
                navArgument("vector1y") {
                    type = NavType.FloatType
                    defaultValue = 1f
                    nullable = false
                },
                navArgument("vector1z") {
                    type = NavType.FloatType
                    defaultValue = 1f
                    nullable = false
                },
                navArgument("vector2x") {
                    type = NavType.FloatType
                    defaultValue = 1f
                    nullable = false
                },
                navArgument("vector2y") {
                    type = NavType.FloatType
                    defaultValue = 1f
                    nullable = false
                },
                navArgument("vector2z") {
                    type = NavType.FloatType
                    defaultValue = 1f
                    nullable = false
                },
            )
        ) { entry ->
            ReportScreen(
                vector1x = entry.arguments!!.getFloat("vector1x"),
                vector1y = entry.arguments!!.getFloat("vector1y"),
                vector1z = entry.arguments!!.getFloat("vector1z"),
                vector2x = entry.arguments!!.getFloat("vector2x"),
                vector2y = entry.arguments!!.getFloat("vector2y"),
                vector2z = entry.arguments!!.getFloat("vector2z"),
            )
        }
    }
}