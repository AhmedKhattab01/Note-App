package com.example.simplenoteapp.screens.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.domain.entity.Category;
import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentFavouriteBinding;
import com.example.simplenoteapp.screens.NotesViewModel;
import com.example.simplenoteapp.utils.adapters.NotesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavouriteFragment extends Fragment {
    private FragmentFavouriteBinding binding;
    private NotesViewModel myViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.add(getString(R.string.select_category));
        binding.spinner.setAdapter(spinnerAdapter);

        NotesAdapter adapter = new NotesAdapter();

        adapter.setOnFavouriteClickListener(note -> {
            note.setFavourite(!note.isFavourite());
            new Thread(() -> {
                myViewModel.updateNote(note);
            }).start();
        });

        myViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            List<String> categoryTitles = categories.stream()
                    .map(Category::getTitle)
                    .collect(Collectors.toList());

            spinnerAdapter.addAll(categoryTitles);

            spinnerAdapter.notifyDataSetChanged();
        });

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                binding.spinner.setSelection(i);
                if (binding.spinner.getSelectedItemPosition() != 0) {
                    myViewModel.getAllNotesByCategoryAndFavourite(spinnerAdapter.getItem(i)).observe(getViewLifecycleOwner(), adapter::submitList);
                }
                else {
                    myViewModel.getAllNotesByFavourite().observe(getViewLifecycleOwner(), notes -> {

                        binding.tvCount.setText(notes.size() + " " + getString(R.string.note));

                        if (!notes.isEmpty()) {
                            adapter.submitList(notes);
                            binding.lottieAnimationView.setVisibility(View.GONE);
                        } else {
                            binding.lottieAnimationView.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.rvNotes.setAdapter(adapter);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}