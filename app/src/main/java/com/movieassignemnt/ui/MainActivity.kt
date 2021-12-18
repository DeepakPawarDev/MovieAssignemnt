package com.movieassignemnt.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.movieassignemnt.R
import com.movieassignemnt.databinding.ActivityMainBinding
import com.movieassignemnt.datasource.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel
    var list: MutableList<Movie?> = mutableListOf<Movie?>()
    private var intPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        setAdapter()
        setObservers()
        setListeners()
        viewModel.getMovieBySearch(intPage, binding.editTxtMovie.text.toString())


    }

    private fun setListeners() {

        binding.imgSearch.setOnClickListener(View.OnClickListener {

            intPage = 1
            list.clear()
            viewModel.getMovieBySearch(intPage, binding.editTxtMovie.getText().toString().trim())
        })
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    intPage++

                    viewModel.getMovieBySearch(
                        intPage,
                        binding.editTxtMovie.getText().toString().trim()
                    )

                }
            }
        })


    }

    private fun setObservers() {


        lifecycleScope.launchWhenStarted {
            viewModel.networkState.collect {

                when (it) {
                    is MainActivityViewModel.NetworkState.Error -> {

                        Toast.makeText(applicationContext, it.errorMSG, Toast.LENGTH_LONG).show()

                    }
                    is MainActivityViewModel.NetworkState.ProgressIn -> {


                        if (it.isProgress) {
                            binding.progressBar.visibility = View.VISIBLE
                        } else {
                            binding.progressBar.visibility = View.GONE
                        }

                    }

                    is MainActivityViewModel.NetworkState.Success -> {

                        it.movieList?.let { it1 ->
                            // list?.clear()
                            list?.addAll(it1)
                            binding.adapter?.notifyDataSetChanged()

                        }
                    }
                }
            }


        }
    }


    private fun setAdapter() {

        val adapter = MovieListRecyclerAdapter(list, this)
        binding.adapter = adapter

    }


}