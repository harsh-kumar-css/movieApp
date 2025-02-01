package com.example.movieapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movieapp.presentation.home.screens.HomeScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.homeGraph(navController: NavController) {
    composable<Home>(){
        HomeScreen()
    }
}

@Serializable
object Home
