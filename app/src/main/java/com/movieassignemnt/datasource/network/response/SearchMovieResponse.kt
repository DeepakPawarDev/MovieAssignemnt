package com.movieassignemnt.datasource.network.response

import com.movieassignemnt.datasource.model.Movie

data class SearchMovieResponse(

    var Search: MutableList<Movie?>?,
    var totalResults: String? ,
    var Response: String? ,
) {
}