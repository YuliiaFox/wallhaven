package com.example.wallhaven.filters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallhaven.R;
import com.example.wallhaven.filters.recyclerview.TagSuggestionAdapter;
import com.example.wallhaven.results.model.Tag;

public class SearchByTagActivity extends AppCompatActivity {
    public static final String TAG = "tag";
    private RecyclerView recyclerView;
    private TagSuggestionAdapter adapter;
    private LinearLayoutManager layoutManager;
    private TagsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_tags);
        viewModel = ViewModelProviders.of(this).get(TagsViewModel.class);

        recyclerView = findViewById(R.id.tag_suggestions);
        TextView noTagsWereFound = findViewById(R.id.no_tags_were_found_label);

        layoutManager = new LinearLayoutManager(this);

        adapter = new TagSuggestionAdapter(position -> {
            String tagId = adapter.getElementByPosition(position).getId();
            String name = adapter.getElementByPosition(position).getValue();
            Intent returnIntent = new Intent();
            Tag tag = new Tag(tagId, name);
            returnIntent.putExtra(TAG, tag);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        viewModel.getTagLiveData().observe(this, tags -> {
            adapter.setData(tags);
            if (tags.isEmpty()) {
                recyclerView.setVisibility(View.INVISIBLE);
                noTagsWereFound.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                noTagsWereFound.setVisibility(View.INVISIBLE);
            }
            adapter.notifyDataSetChanged();
        });

        TextView tagInputField = findViewById(R.id.tag_input);
        tagInputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.searchTags(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }
}