package com.example.wallhaven.searchbytag;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallhaven.R;
import com.example.wallhaven.searchbytag.model.Tag;
import com.example.wallhaven.searchingresults.recyclerview.SearchResultsAdapter;

public class SearchByTagActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchResultsAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_tags);

        recyclerView = findViewById(R.id.tag_suggestions);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(adapter);
    }
}