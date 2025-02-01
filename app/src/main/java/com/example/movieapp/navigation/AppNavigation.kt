package com.example.movieapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.presentation.home.HomeViewModel

@Composable
fun AppNavigation() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val isConnected by viewModel.isConnected.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        {
            Column {
                if (!isConnected) {
                    NetworkAlert()
                }
                Row {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController, startDestination = Search
                    ) {
                        homeGraph(navController)
                        searchGraph(navController)
                        detailsGraph(navController)
                    }
                }
            }
        }
    }
}

@Composable
private fun NetworkAlert(modifier : Modifier = Modifier) {
    Row {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(color = Color.Red)
        ) {
            Text(
                color = Color.White,
                text = "Your are not connected to internet !"
            )
        }
    }
}