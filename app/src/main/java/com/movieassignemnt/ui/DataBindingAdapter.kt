package com.movieassignemnt.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object DataBindingAdapter {
    @JvmStatic
    @BindingAdapter("app:posterImage")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Picasso.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}