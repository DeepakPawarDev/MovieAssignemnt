package com.movieassignemnt.ui

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movieassignemnt.R
import com.movieassignemnt.databinding.MovieRowBinding
import com.movieassignemnt.datasource.model.Movie
import com.movieassignemnt.ui.moviediscription.MovieDetailsActivity
import com.squareup.picasso.Picasso


class MovieListRecyclerAdapter(val movieList: MutableList<Movie?>?, var activity: Activity) :
    RecyclerView.Adapter<MovieListRecyclerAdapter.ViewHolder>() {


    lateinit var context:Activity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MovieRowBinding = DataBindingUtil.inflate(
            activity!!.layoutInflater,
            R.layout.movie_row,
            parent,
            false
        )




        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movieList!![position]?.let { holder.setData(it) }
    }

    override fun getItemCount(): Int {
        return movieList?.size!!
    }

    class ViewHolder(itemView: MovieRowBinding) :
        RecyclerView.ViewHolder(itemView.getRoot()) {
        var view: MovieRowBinding
        fun setData(movie: Movie) {

            view.movie = movie


            view.imageMovie.setOnClickListener {

                var intent=Intent(itemView.context,MovieDetailsActivity::class.java)
                intent.putExtra("movie",movie)
                itemView.context.startActivity(intent)
            }
        }

        init {
            view = itemView
        }
    }
}
