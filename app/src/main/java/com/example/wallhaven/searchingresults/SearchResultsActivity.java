package com.example.wallhaven.searchingresults;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.wallhaven.R;
import com.example.wallhaven.imagedetails.ImageDetailsActivity;
import com.example.wallhaven.searchingresults.model.Image;
import com.example.wallhaven.searchingresults.recyclerview.SearchResultsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchResultsActivity extends AppCompatActivity {

    public static final String ID = "id";

    private RecyclerView recyclerView;
    private SearchResultsAdapter adapter;
    private StaggeredGridLayoutManager layoutManager;
    ImagesViewModel imagesViewModel = ViewModelProviders.of(this).get(ImagesViewModel.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        initRecycleViewForSearchResults();
        initAutocompleteAdapter();

    }

    private void initRecycleViewForSearchResults(){
        recyclerView = findViewById(R.id.search_image_result_recycleView);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SearchResultsAdapter(Image.DIFF_CALLBACK, position -> {
            String id =  Objects.requireNonNull(adapter.getCurrentList().get(position)).id;
            Intent intent = new Intent(this, ImageDetailsActivity.class);
            intent.putExtra(ID, id);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        imagesViewModel.getImages().observe(this, images -> adapter.submitList(images));
    }

    private void initAutocompleteAdapter(){
        List<String> autocompleteSuggestions =new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_autocomplete_line,autocompleteSuggestions);
    }
}
