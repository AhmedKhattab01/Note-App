package com.example.simplenoteapp.utils.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.entity.Category;
import com.example.simplenoteapp.databinding.ItemRvCategoryBinding;
import com.example.simplenoteapp.screens.home.HomeFragmentDirections;
import com.example.simplenoteapp.utils.differs.CategoryDiffItemCallback;

public class CategoryAdapter extends ListAdapter<Category, CategoryAdapter.ViewHolder> {

    public CategoryAdapter() {
        super(new CategoryDiffItemCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return new CategoryAdapter.ViewHolder(ItemRvCategoryBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRvCategoryBinding binding;

        public ViewHolder(@NonNull ItemRvCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Category category) {
            binding.setCategory(category);

            binding.getRoot().setOnClickListener(view -> {
                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToNotesFragment(category));
            });

            binding.executePendingBindings();
        }
    }
}
