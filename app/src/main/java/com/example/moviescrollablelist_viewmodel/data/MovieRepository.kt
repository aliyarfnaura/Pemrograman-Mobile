package com.example.moviescrollablelist_viewmodel.data

import com.example.moviescrollablelist_viewmodel.data.MovieData
import com.example.moviescrollablelist_viewmodel.data.movieList

class MovieRepository {
    fun getMovies(): List<MovieData> {
        return movieList
    }

    fun getMovieById(movieId: Int): MovieData? {
        return movieList.find { it.id == movieId }
    }
}
