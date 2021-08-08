package com.jm4488.booksearch

import android.text.TextUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) return
    val ctx = imageView.context
    if (ctx is AppCompatActivity && ctx.isFinishing) return

    val options = RequestOptions().apply {
        diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        dontAnimate()
    }

    Glide.with(ctx)
        .load(url)
        .apply(options)
        .into(imageView)
}