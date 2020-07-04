package com.example.wallhaven.searchbytag.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallhaven.searchbytag.model.Tag;

public class SuggestedTagViewHolder extends RecyclerView.ViewHolder {
    private TextView tagView;
    public SuggestedTagViewHolder(@NonNull View itemView, OnTagClickListener onTagClickListener) {
        super(itemView);
        tagView = ((TextView)itemView);
        tagView.setOnClickListener(v -> onTagClickListener.onItemClick(getAdapterPosition()));
    }

    public void bindData(Tag tag){
        tagView.setText(tag.getValue());
    }
}
