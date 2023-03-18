package com.example.simplenoteapp.utils.differs;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.domain.entity.Note;

import java.util.Objects;

public class NoteDiffItemCallback extends DiffUtil.ItemCallback<Note> {

    @Override
    public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return Objects.equals(oldItem,newItem);
    }
}
