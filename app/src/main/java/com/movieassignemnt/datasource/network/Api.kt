package com.movieassignemnt.datasource.network

import com.movieassignemnt.datasource.network.response.SearchMovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @Headers("Content-Type: application/json")
    @GET("/")
   suspend fun getMovies(
        @Query("page") pageNumber: Int,
        @Query("s") strSearchMovie: String?,
        @Query("apikey") strAPIKey: String?
    ): Response<SearchMovieResponse?>

}