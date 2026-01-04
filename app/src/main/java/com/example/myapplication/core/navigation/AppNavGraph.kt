package com.example.myapplication.core.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.screen.catalog.CatalogScreen
import com.example.myapplication.screen.detail.DetailScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.CATALOG
    ) {

        composable(Routes.CATALOG) {
            CatalogScreen(
                onVideoClick = { videoUrl ->
                    val encodedUrl = Uri.encode(videoUrl)
                    navController.navigate("${Routes.DETAIL}/$encodedUrl")
                }
            )
        }

        composable(
            route = "${Routes.DETAIL}/{videoUrl}",
            arguments = listOf(
                navArgument("videoUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val videoUrl = backStackEntry.arguments?.getString("videoUrl")!!
            DetailScreen(
                videoUrl = Uri.decode(videoUrl),
                navController = navController
            )
        }
    }

}
