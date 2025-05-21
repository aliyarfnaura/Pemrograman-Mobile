package com.example.moviescrollablelist_viewmodel.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviescrollablelist_viewmodel.data.MovieRepository

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

fun viewModelFactory(): ViewModelProvider.Factory {
    val repository = MovieRepository()
    return MovieViewModelFactory(repository)
}
