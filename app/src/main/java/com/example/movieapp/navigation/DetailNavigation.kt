package com.example.movieapp.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.movieapp.util.Constants.DEEPLINK_HOME_HOST
import com.example.movieapp.util.Constants.DEEPLINK_SCHEME
import kotlinx.serialization.Serializable

fun NavGraphBuilder.detailsGraph(navController: NavController){
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

@Serializable
data class Detail(val id : Int)