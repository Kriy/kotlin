package com.example.weiyue.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;

public class ImageLoaderUtil {

    public static void LoadImage(Context context, Object url, ImageView imageView) {
        Glide.with(context).load(url).
                apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(new DrawableTransitionOptions().crossFade(800))
                .into(imageView);
    }

    public static void LoadImage(Context context, Object url, ImageView imageView, RequestOptions requestOptions) {
        Glide.with(context).load(url)
                .apply(requestOptions)
                .into(imageView);
    }


    public static void LoadImage(Fragment fragment, Object url, ImageView imageView, RequestOptions requestOptions) {
        Glide.with(fragment).load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    public static void LoadImage(Context context, Object url, ImageViewTarget<android.graphics.drawable.Drawable> imageViewTarget) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageViewTarget);
    }

    public static void LoadImage(Context context, Object url, ImageView imageView, RequestListener listener) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .listener(listener)
                .into(imageView);
    }

}
