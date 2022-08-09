package com.example.vectorcalc

sealed class Screen (val route : String) {
    object MainScreen : Screen("main_screen")
    object ReportScreen : Screen("report_screen")

    fun withArgs(vararg arg: Float): String
    {
        return buildString {
            append(route)
            arg.forEach { arg ->
                append("/$arg")
            }
        }
    }
}