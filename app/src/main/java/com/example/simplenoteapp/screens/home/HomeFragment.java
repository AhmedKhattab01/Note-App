package com.example.simplenoteapp.screens.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentHomeBinding;
import com.example.simplenoteapp.screens.NotesViewModel;
import com.example.simplenoteapp.utils.adapters.CategoryAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private NotesViewModel myViewModel;
    private CategoryAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        setupRecyclerView();
        onRecyclerViewScroll();
        onFabClick();

        myViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            adapter.submitList(categories);
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    // Hide fab on scroll
    private void onRecyclerViewScroll() {
        binding.rvCategories.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && binding.floatingActionButton.isShown()) {
                    // Scrolling down
                    binding.floatingActionButton.hide();
                } else if (dy < 0 && !binding.floatingActionButton.isShown()) {
                    // Scrolling up
                    binding.floatingActionButton.show();
                }
            }
        });
    }

    // initialize adapter
    // set adapter
    private void setupRecyclerView() {
        adapter = new CategoryAdapter();
        binding.rvCategories.setAdapter(adapter);
    }

    private void onFabClick() {
        binding.floatingActionButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addCategoryFragment));
    }
}