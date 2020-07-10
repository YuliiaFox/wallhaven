package com.example.wallhaven.imagedetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.wallhaven.R;
import com.example.wallhaven.results.SearchResultsActivity;

import org.apache.commons.io.FileUtils;

public class ImageDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        Intent intent = getIntent();
        String id = intent.getStringExtra(SearchResultsActivity.ID);

        ImageDetailsViewModel viewModel = ViewModelProviders.of(this).get(ImageDetailsViewModel.class);
        viewModel.getImageDetailsMutableLiveData(id).observe(this, imageDetails -> {
            ImageView imageView = findViewById(R.id.big_image);
            TextView category = findViewById(R.id.category);
            TextView ratio = findViewById(R.id.ratio);
            TextView resolution = findViewById(R.id.image_resolution);
            TextView fileSize = findViewById(R.id.file_size);
            TextView tags = findViewById(R.id.tags);

           Glide.with(this).load(imageDetails.getPath()).into(imageView);
            category.setText(getString(R.string.category_label, imageDetails.getCategory()));
            ratio.setText(getString(R.string.ratio_label, imageDetails.getRatio()));
            resolution.setText(getString(R.string.resolution_label, imageDetails.getResolution()));
            fileSize.setText(getString(R.string.file_size_label, FileUtils.byteCountToDisplaySize(imageDetails.getFileSize())));
            tags.setText(getString(R.string.tags_label, imageDetails.getTags()));
        });
    }
}
