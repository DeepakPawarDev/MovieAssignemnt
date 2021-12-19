package com.movieassignemnt.datasource.model

import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


data class MovieDetails(

    @SerializedName("Title")
    var title: String?,

    @SerializedName("Year")
    var year: String?,

    @SerializedName("Rated")
    var rated: String?,

    @SerializedName("Released")
    var released: String?,

    @SerializedName("Runtime")
    var runtime: String?,

    @SerializedName("Genre")
    var genre: String?,

    @SerializedName("Director")
    var director: String?,

    @SerializedName("Writer")
    var writer: String?,

    @SerializedName("Actors")
    var actors: String?,

    @SerializedName("Plot")
    var plot: String?,

    @SerializedName("Language")
    var language: String?,

    @SerializedName("Country")
    var country: String?,

    @SerializedName("Awards")
    var awards: String?,

    @SerializedName("Poster")
    var poster: String?,

    @SerializedName("Ratings")
    var ratings: List<Rating>?,

    @SerializedName("Metascore")
    var metascore: String?,
    var imdbRating: String?,
    var imdbVotes: String?,
    var imdbID: String?,

    @SerializedName("Type")
    var type: String?,

    @SerializedName("DVD")
    var dVD: String?,

    @SerializedName("BoxOffice")
    var boxOffice: String?,

    @SerializedName("Production")
    var production: String?,

    @SerializedName("Website")
    var website: String?,

    @SerializedName("Response")
    var response: String?,
)

