package com.dpearth.dvox;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;


public class ImageBinder {
    private ImageBinder() {
    }

    @BindingAdapter("android:src")
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}