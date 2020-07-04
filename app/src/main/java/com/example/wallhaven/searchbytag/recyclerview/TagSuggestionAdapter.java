package com.example.wallhaven.searchbytag.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.wallhaven.R;
import com.example.wallhaven.searchbytag.model.Tag;

import java.util.Objects;

public class TagSuggestionAdapter extends PagedListAdapter<Tag, SuggestedTagViewHolder>{

    private final OnTagClickListener onTagClickListener;

    protected TagSuggestionAdapter(@NonNull DiffUtil.ItemCallback<Tag> diffCallback, OnTagClickListener onTagClickListener) {
        super(diffCallback);
        this.onTagClickListener = onTagClickListener;
    }

    @NonNull
    @Override
    public SuggestedTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tagView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_autocomplete_line, parent, false);
        SuggestedTagViewHolder vh = new SuggestedTagViewHolder(tagView,onTagClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedTagViewHolder holder, int position) {
        holder.bindData(Objects.requireNonNull(getItem(position)));
    }

}
