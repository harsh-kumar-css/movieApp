package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.movieapp.Constants.DEEPLINK_HOME_HOST
import com.example.movieapp.Constants.DEEPLINK_SCHEME
import com.example.movieapp.presentation.home.screens.HomeScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Box(modifier = Modifier.padding(innerPadding))
                   {
                       val navController = rememberNavController()
                       NavHost(navController = navController, startDestination = Search){
                           composable<Home>(){
                               HomeScreen()
                           }
                           composable<Search>(){
                                Button(onClick = { navController.navigate(Home) }) {
                                   Text("Home")
                                }
                           }
                           composable<Detail>(
                               deepLinks = listOf(
                                   navDeepLink<Detail>(
                                       basePath = "$DEEPLINK_SCHEME://$DEEPLINK_HOME_HOST"
                                   )
                               )
                           ){
                               val id = it.toRoute<Detail>().id
                               Text(text = id.toString())
                           }
                       }
                   }
                }
            }
        }
    }
}

@Serializable
object Home

@Serializable
object Search

@Serializable
data class Detail(val id : Int)


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}