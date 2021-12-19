package com.movieassignemnt.ui.moviediscription

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieassignemnt.datasource.model.Movie
import com.movieassignemnt.datasource.model.MovieDetails
import com.movieassignemnt.datasource.repository.MovieRepository
import com.movieassignemnt.ui.MainActivityViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(val movieRepository: MovieRepository) :
    ViewModel() {



    private val searchMovieMutableState =
        MutableStateFlow<NetworkState>(NetworkState.ProgressIn(false))

     val networkState= searchMovieMutableState.asStateFlow()

    fun getMovieDetails(strMovie:String) {

        searchMovieMutableState.value=NetworkState.ProgressIn(true)
        viewModelScope.launch {

            movieRepository.getMovieDetails(strMovie)
                . catch {

                    var errorMSG:String=it.localizedMessage
                    if (it is IOException){
                        errorMSG= "Please check internet connection"
                    }
                    searchMovieMutableState.value = MovieDetailsViewModel.NetworkState.Error(errorMSG)
                    searchMovieMutableState.value= MovieDetailsViewModel.NetworkState.ProgressIn(false)

                }.collect {

                searchMovieMutableState.value=NetworkState.ProgressIn(false)

                if (it.isSuccessful) {

                    it.body()?.let {

                        searchMovieMutableState.value = NetworkState.Success(it)

                    }
                }

            }

        }

    }



    sealed class NetworkState {

        class Error(var errorMSG: String) : NetworkState()
        class Success(var movie: MovieDetails?) : NetworkState()
        class ProgressIn(var isProgress: Boolean) : NetworkState()
    }

}