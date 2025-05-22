package com.example.moviescrollablelist_viewmodel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviescrollablelist_viewmodel.data.MovieViewModel
import com.example.moviescrollablelist_viewmodel.data.viewModelFactory
import com.example.moviescrollablelist_viewmodel.ui.MovieDetail
import com.example.moviescrollablelist_viewmodel.ui.MovieList
import com.example.moviescrollablelist_viewmodel.ui.theme.MovieListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieListTheme {
                val navController = rememberNavController()
                val movieViewModel: MovieViewModel = viewModel(factory = viewModelFactory())

                LaunchedEffect(Unit) {
                    Log.d("MainActivity", "MainActivity loaded")
                }

                NavHost(
                    navController = navController,
                    startDestination = "movieList"
                ) {
                    composable("movieList") {
                        MovieList(navController = navController)
                    }
                    composable(
                        "movieDetail/{desc}/{image}",
                        arguments = listOf(
                            navArgument("desc") { type = NavType.StringType },
                            navArgument("image") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val desc = backStackEntry.arguments?.getString("desc") ?: ""
                        val image = backStackEntry.arguments?.getInt("image") ?: 0
                        MovieDetail(navController = navController, desc = desc, image = image)
                    }
                }
            }
        }
    }
}