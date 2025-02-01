package com.example.movieapp.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.searchGraph(navController: NavController) {
    composable<Search>(){
        Button(onClick = { navController.navigate(Home) }) {
            Text("Home")
        }
    }
}

@Serializable
object Search
