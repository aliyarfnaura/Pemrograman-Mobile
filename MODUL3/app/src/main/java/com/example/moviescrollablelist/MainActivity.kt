package com.example.moviescrollablelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviescrollablelist.ui.MovieDetail
import com.example.moviescrollablelist.ui.MovieList
import com.example.moviescrollablelist.ui.theme.MovieListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MovieListTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "movieList") {
                        composable("movieList") {
                            MovieList(navController)
                        }
                        composable(
                            "movieDetail/{description}/{image}",
                            arguments = listOf(
                                navArgument("description") { type = NavType.StringType },
                                navArgument("image") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val desc = backStackEntry.arguments?.getString("description") ?: ""
                            val image = backStackEntry.arguments?.getInt("image") ?: 0
                            MovieDetail(navController = navController, desc = desc, image = image)
                        }

                    }
                }
            }
        }
    }
}
