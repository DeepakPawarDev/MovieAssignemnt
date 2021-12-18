package com.movieassignemnt.ui.moviediscription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.movieassignemnt.R
import com.movieassignemnt.databinding.ActivityMovieDetailsBinding
import com.movieassignemnt.datasource.model.Movie
import com.squareup.picasso.Picasso

class MovieDetails : AppCompatActivity() {

    lateinit var binding:ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        setData()

    }

    private fun setData() {
        val movie = intent.getSerializableExtra("movie") as Movie?
        binding.movie=movie

        Picasso.with(this)
            .load(movie?.Poster)
            .into(binding.imageMovie)
    }
}