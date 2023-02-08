package com.example.composebook01.ui

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object SplashScreen: Screen("splash_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
