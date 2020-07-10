package com.example.wallhaven.filters.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallhaven.R;
import com.example.wallhaven.filters.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagSuggestionAdapter extends RecyclerView.Adapter<SuggestedTagViewHolder> {

    private final OnTagClickListener onTagClickListener;
    private List<Tag> data;

    public TagSuggestionAdapter(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public SuggestedTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tagView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_tag_line, parent, false);
        SuggestedTagViewHolder vh = new SuggestedTagViewHolder(tagView,onTagClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedTagViewHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Tag> data) {
        this.data = data;
    }

    public Tag getElementByPosition(int position) {
        return data.get(position);
    }
}
