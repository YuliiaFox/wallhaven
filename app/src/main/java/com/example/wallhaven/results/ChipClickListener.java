package com.example.wallhaven.results;

import android.view.View;
import android.view.ViewGroup;

import com.example.wallhaven.results.model.Tag;

public class ChipClickListener implements View.OnClickListener {
    ImagesViewModel viewModel;
    Tag tag;

    public ChipClickListener(ImagesViewModel viewModel, Tag tag) {
        this.viewModel = viewModel;
        this.tag = tag;
    }

    @Override
    public void onClick(View v) {
        ((ViewGroup) v.getParent()).removeView(v);
        viewModel.refreshDataWithoutTag(tag);
    }
}
