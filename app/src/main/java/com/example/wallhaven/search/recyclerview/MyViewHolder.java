package com.example.wallhaven.search;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallhaven.R;

class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public MyViewHolder(@NonNull View itemView, OnImageClickListener onImageClickListener) {
        super(itemView);
        imageView = ((ImageView) itemView);
        imageView.setOnClickListener(v -> onImageClickListener.onItemClick(getAdapterPosition()));
    }

    public void bindData(ListImage listImage){
        Glide.with(imageView.getContext()).load(listImage.getUrl()).placeholder(R.drawable.ic_launcher_background).into(imageView);
    }
}
