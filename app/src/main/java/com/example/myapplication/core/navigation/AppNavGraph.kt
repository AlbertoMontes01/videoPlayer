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
import com.example.myapplication.screen.player.FullscreenPlayerScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.CATALOG
    ) {

        // 1️⃣ CATALOG
        composable(Routes.CATALOG) {
            CatalogScreen(
                onVideoClick = { videoUrl ->
                    navController.navigate(
                        "${Routes.DETAIL}/${Uri.encode(videoUrl)}"
                    )
                }
            )
        }

        // 2️⃣ DETAIL
        composable(
            route = "${Routes.DETAIL}/{videoUrl}",
            arguments = listOf(
                navArgument("videoUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val videoUrl =
                Uri.decode(backStackEntry.arguments!!.getString("videoUrl")!!)

            DetailScreen(
                videoUrl = videoUrl,
                navController = navController
            )
        }

        // 3️⃣ FULLSCREEN
        composable(
            route = "${Routes.FULLSCREEN}/{videoUrl}",
            arguments = listOf(
                navArgument("videoUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val videoUrl =
                Uri.decode(backStackEntry.arguments!!.getString("videoUrl")!!)

            FullscreenPlayerScreen(
                videoUrl = videoUrl,
                navController = navController
            )
        }
    }
}
