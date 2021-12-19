package com.movieassignemnt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieassignemnt.datasource.model.Movie
import com.movieassignemnt.datasource.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val movieRepository: MovieRepository) :
    ViewModel() {



    private val searchMovieMutableState =
        MutableStateFlow<NetworkState>(NetworkState.ProgressIn(false))

     val networkState: StateFlow<NetworkState> = searchMovieMutableState


    fun getMovieBySearch( intPage:Int, strMovie:String) {

        searchMovieMutableState.value=NetworkState.ProgressIn(true)
        viewModelScope.launch {

            movieRepository.getMovieListBySearch(intPage, strMovie).
            catch {

                var errorMSG:String=it.localizedMessage
                if (it is IOException){
                  errorMSG= "Please check internet connection"
                }
                searchMovieMutableState.value = NetworkState.Error(errorMSG)
                searchMovieMutableState.value=NetworkState.ProgressIn(false)

            }.collect {

                searchMovieMutableState.value=NetworkState.ProgressIn(false)

                if (it.isSuccessful) {

                    it.body()?.let {

                        searchMovieMutableState.value = NetworkState.Success(it.Search)

                    }
                }

            }

        }

    }



    sealed class NetworkState {

        class Error(var errorMSG: String) : NetworkState()
        class Success(var movieList: List<Movie?>?) : NetworkState()
        class ProgressIn(var isProgress: Boolean) : NetworkState()
    }

}