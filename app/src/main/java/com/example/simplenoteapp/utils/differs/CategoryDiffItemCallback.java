package com.example.simplenoteapp.utils.differs;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.domain.entity.Category;

import java.util.Objects;

public class CategoryDiffItemCallback extends DiffUtil.ItemCallback<Category> {
    @Override
    public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
        return Objects.equals(oldItem,newItem);
    }
}
