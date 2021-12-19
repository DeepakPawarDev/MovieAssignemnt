package com.movieassignemnt.datasource.repository

import com.movieassignemnt.datasource.model.MovieDetails
import com.movieassignemnt.datasource.network.Api
import com.movieassignemnt.datasource.network.response.SearchMovieResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
@ViewModelScoped
class MovieRepository @Inject constructor(val api: Api) {


    suspend fun getMovieListBySearch(intPage: Int, strMovie: String) = flow<Response<SearchMovieResponse?>> {

        val response = api.getMovies(intPage, strMovie, "55d669e0")
        emit(response)
    }.flowOn(Dispatchers.Main)


    suspend fun getMovieDetails(strMovie: String) = flow<Response<MovieDetails?>> {

        val response = api.getMovieDetail( strMovie, "55d669e0")
        emit(response)
    }.flowOn(Dispatchers.Main)

}