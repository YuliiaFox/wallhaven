package com.example.wallhaven.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public final class ListImage {
    public String url;
    public String id;

    public ListImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public static DiffUtil.ItemCallback<ListImage> DIFF_CALLBACK = new DiffUtil.ItemCallback<ListImage>() {
        @Override
        public boolean areItemsTheSame(@NonNull ListImage oldItem, @NonNull ListImage newItem) {
            return oldItem.url == newItem.url;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListImage oldItem, @NonNull ListImage newItem) {
            return oldItem.url.equals(newItem.url);
        }
    };

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == this){
            return true;
        }
        ListImage listImage = (ListImage) obj;
        return listImage.url.equals(this.url);
    }
}
