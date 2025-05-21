package com.example.moviescrollablelist_viewmodel.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescrollablelist_viewmodel.data.MovieRepository
import com.example.moviescrollablelist_viewmodel.data.MovieData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movieList = MutableStateFlow<List<MovieData>>(emptyList())
    val movieList: StateFlow<List<MovieData>> get() = _movieList

    private val _selectedMovie = MutableStateFlow<MovieData?>(null)
    val selectedMovie: StateFlow<MovieData?> get() = _selectedMovie

    init {
        Log.d("MovieViewModel", "ViewModel created")
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _movieList.value = repository.getMovies()
            Log.d("MovieViewModel", "Movies loaded: ${_movieList.value.size}")
        }
    }

    fun selectMovie(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.getMovieById(movieId)
            _selectedMovie.value = movie
            Log.d("MovieViewModel", "Selected movie: ${movie?.name}")
        }
    }
}
