package com.example.wallhaven.results.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.wallhaven.R;
import com.example.wallhaven.results.model.Image;

import java.util.Objects;

public class SearchResultsAdapter extends PagedListAdapter<Image, ImageViewHolder> {

    private final OnImageClickListener onImageClickListener;

    public SearchResultsAdapter(@NonNull DiffUtil.ItemCallback<Image> diffCallback, OnImageClickListener onImageClickListener) {
        super(diffCallback);
        this.onImageClickListener = onImageClickListener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View imageView = LayoutInflater.
                from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        ImageViewHolder vh = new ImageViewHolder(imageView, onImageClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bindData(Objects.requireNonNull(getItem(position)));
    }
}
