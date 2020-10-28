package com.example.wallhaven.results;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wallhaven.R;
import com.example.wallhaven.imagedetails.ImageDetailsActivity;
import com.example.wallhaven.results.model.Image;
import com.example.wallhaven.results.model.SearchParameters;
import com.example.wallhaven.results.recyclerview.SearchResultsAdapter;
import com.example.wallhaven.tags.SearchByTagActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Objects;

public class SearchResultsActivity extends AppCompatActivity {
    private static final int TAG_REQUEST_CODE = 1;
    public static final String ID = "id";

    private RecyclerView recyclerView;
    private SearchResultsAdapter adapter;
    private StaggeredGridLayoutManager layoutManager;
    ImagesViewModel imagesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_results);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SwipeRefreshLayout swipeContainer = findViewById(R.id.swipeContainer);

        imagesViewModel = ViewModelProviders.of(this).get(ImagesViewModel.class);
        initRecycleViewForSearchResults();

        imagesViewModel.getImages().observe(this, images -> adapter.submitList(images));
        imagesViewModel.getTags().observe(this, tags -> {
            removeChips();
            tags.stream().forEach(this::addTagIdToChipGroup);
            swipeContainer.setRefreshing(false);
        });

        swipeContainer.setOnRefreshListener(() -> {
            imagesViewModel.refreshDataWithSameTags();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAG_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            SearchParameters tag = data.getParcelableExtra(SearchByTagActivity.TAG);
            imagesViewModel.loadDataWithNewTag(tag);
            addTagIdToChipGroup(tag);
        }
    }

    private void initRecycleViewForSearchResults() {
        recyclerView = findViewById(R.id.search_image_result_recycleView);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SearchResultsAdapter(Image.DIFF_CALLBACK, position -> {
            String id = Objects.requireNonNull(adapter.getCurrentList().get(position)).id;
            Intent intent = new Intent(this, ImageDetailsActivity.class);
            intent.putExtra(ID, id);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filters_button:
                return true;
            case R.id.search_by_tags_button:
                Intent intent = new Intent(this, SearchByTagActivity.class);
                startActivityForResult(intent, TAG_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addTagIdToChipGroup(SearchParameters tag) {
        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        Chip chip = (Chip) getLayoutInflater().inflate(R.layout.item_chip, chipGroup, false);
        chip.setText(tag.getTagName());
        chip.setId(View.generateViewId());
        chipGroup.addView(chip);
        ChipClickListener chipClickListener = new ChipClickListener(imagesViewModel, tag);
        chip.setOnCloseIconClickListener(chipClickListener);
    }

    public void removeChips() {
        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        chipGroup.removeAllViews();
    }
}
