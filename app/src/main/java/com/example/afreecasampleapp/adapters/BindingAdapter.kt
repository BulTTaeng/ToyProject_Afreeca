package com.example.afreecasampleapp.adapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("imageFromUrlHttp")
fun bindImageFromUrlHttp(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        return
    }

    Glide.with(view.context)
        .load("https:$imageUrl")
        .into(view)
}


@BindingAdapter("imageFromUrlCircleCrop")
fun bindImageFromUrlCircleCrop(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        return
    }

    Glide.with(view.context)
        .load("https:$imageUrl")
        .circleCrop()
        .into(view)
}