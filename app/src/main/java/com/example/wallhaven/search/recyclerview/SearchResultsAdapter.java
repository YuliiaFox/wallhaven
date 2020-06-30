package com.example.wallhaven.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.wallhaven.R;

public class SearchResultsAdapter extends PagedListAdapter<ListImage, MyViewHolder> {

    private final OnImageClickListener onImageClickListener;

    protected SearchResultsAdapter(@NonNull DiffUtil.ItemCallback<ListImage> diffCallback, OnImageClickListener onImageClickListener) {
        super(diffCallback);

        this.onImageClickListener = onImageClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View imageView =  LayoutInflater.
                from(parent.getContext())
                .inflate(R.layout.image_view_holder, parent, false);
        MyViewHolder vh = new MyViewHolder(imageView, onImageClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //String imageUrl = dataSet.get(position).thumbs.small;
        holder.bindData(getItem(position));
        //holder.imageView.setImageDrawable((imageUrl));
    }
}
