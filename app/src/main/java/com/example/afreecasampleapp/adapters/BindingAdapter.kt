package com.example.afreecasampleapp.adapters

import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.afreecasampleapp.R

@BindingAdapter("imageFromUrlHttp")
fun bindImageFromUrlHttp(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        return
    }
    view.load("https:$imageUrl")
}


@BindingAdapter("imageFromUrlCircleCrop")
fun bindImageFromUrlCircleCrop(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        return
    }

    glideE(view , imageUrl)
}


fun glideE(view: ImageView, imageUrl: String?){

    Glide.with(view.context)
        .load("https:$imageUrl")
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {

                Handler(Looper.getMainLooper()).postDelayed(Runnable {
                    glideE(view , imageUrl)
                }, 1000)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        })
        .circleCrop()
        .apply(RequestOptions().override(120, 120))
        .timeout(5000)
        .thumbnail(Glide.with(view.context).load(R.drawable.loading))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}