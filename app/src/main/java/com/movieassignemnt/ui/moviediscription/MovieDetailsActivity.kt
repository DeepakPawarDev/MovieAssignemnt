package com.movieassignemnt.ui.moviediscription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.movieassignemnt.R
import com.movieassignemnt.databinding.ActivityMovieDetailsBinding
import com.movieassignemnt.datasource.model.Movie
import com.movieassignemnt.ui.MainActivityViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    lateinit var viewModel: MovieDetailsViewModel
    lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        setData()
        setObservers()

    }

    private fun setData() {
        val movie = intent.getSerializableExtra("movie") as Movie?
      viewModel.getMovieDetails(movie?.imdbID!!)

    }


    private fun setObservers() {


        lifecycleScope.launchWhenStarted {
            viewModel.networkState.collect {

                when (it) {
                    is MovieDetailsViewModel.NetworkState.Error -> {

                        Toast.makeText(applicationContext, it.errorMSG, Toast.LENGTH_LONG).show()

                    }
                    is MovieDetailsViewModel.NetworkState.ProgressIn -> {


                        if (it.isProgress) {
                            binding.progressBar.visibility = View.VISIBLE
                        } else {
                            binding.progressBar.visibility = View.GONE
                        }

                    }

                    is MovieDetailsViewModel.NetworkState.Success -> {

                        binding.movie=it.movie


                    }
                }
            }


        }
    }

}