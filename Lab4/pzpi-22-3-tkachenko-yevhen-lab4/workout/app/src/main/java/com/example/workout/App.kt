
package com.example.workout

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.workout.presentation.*

@Composable
fun App() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "login") {
        composable("login")       { LoginScreen(nav) }
        composable("register")    { RegisterScreen(nav) }
        composable("sessions")    { SessionsScreen(nav) }
        composable("metrics/{id}") { backStack ->
            MetricsScreen(backStack.arguments!!.getString("id")!!.toLong())
        }
        composable("rec/{id}") { backStack ->
            RecScreen(backStack.arguments!!.getString("id")!!.toLong())
        }
        composable("admin") { AdminScreen(nav) }
    }
}
