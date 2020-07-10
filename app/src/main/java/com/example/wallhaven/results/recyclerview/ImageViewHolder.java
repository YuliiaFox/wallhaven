package com.example.wallhaven.results.recyclerview;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallhaven.R;
import com.example.wallhaven.results.model.Image;

class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;

    public ImageViewHolder(@NonNull View itemView, OnImageClickListener onImageClickListener) {
        super(itemView);
        imageView = ((ImageView) itemView);
        imageView.setOnClickListener(v -> onImageClickListener.onItemClick(getAdapterPosition()));
    }

    public void bindData(Image image){
        Glide.with(imageView.getContext()).load(image.getUrl()).placeholder(R.drawable.ic_launcher_background).into(imageView);
    }
}
