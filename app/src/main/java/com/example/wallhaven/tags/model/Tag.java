package com.example.wallhaven.tags.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Tag {
    private String purity;
    private String category;
    private String id;
    private String filterBy;
    private String value;
    private String label;

    public Tag(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public static DiffUtil.ItemCallback<Tag> DIFF_CALLBACK = new DiffUtil.ItemCallback<Tag>() {
        @Override
        public boolean areItemsTheSame(@NonNull Tag oldItem, @NonNull Tag newItem) {
            return oldItem.value == newItem.value;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Tag oldItem, @NonNull Tag newItem) {
            return oldItem.value.equals(newItem.value);
        }
    };

    public String getPurity() {
        return purity;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
