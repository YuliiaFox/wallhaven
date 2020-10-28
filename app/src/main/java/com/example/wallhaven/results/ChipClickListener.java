package com.example.wallhaven.results;

import android.view.View;
import android.view.ViewGroup;

import com.example.wallhaven.results.model.SearchParameters;

public class ChipClickListener implements View.OnClickListener {
    ImagesViewModel viewModel;
    SearchParameters tag;

    public ChipClickListener(ImagesViewModel viewModel, SearchParameters tag) {
        this.viewModel = viewModel;
        this.tag = tag;
    }

    @Override
    public void onClick(View v) {
        ((ViewGroup) v.getParent()).removeView(v);
        viewModel.refreshDataWithoutTag(tag);
    }
}
