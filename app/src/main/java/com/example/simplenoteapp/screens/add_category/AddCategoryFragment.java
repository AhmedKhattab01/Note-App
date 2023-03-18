package com.example.simplenoteapp.screens.add_category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.domain.entity.Category;
import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentAddCategoryBinding;
import com.example.simplenoteapp.screens.NotesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddCategoryFragment extends Fragment {
    private FragmentAddCategoryBinding binding;

    private NotesViewModel myViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddCategoryBinding.inflate(inflater, container, false);

        binding.btnSave.setOnClickListener(view -> {
            if (binding.etTitle.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.all_fields_required), Toast.LENGTH_SHORT).show();
            }
            else {
                new Thread(() -> {
                    myViewModel.insertCategory(new Category(0,binding.etTitle.getText().toString()));
                }).start();
                Navigation.findNavController(view).popBackStack();
            }
        });

        binding.btnCancel.setOnClickListener(view -> {
            Navigation.findNavController(view).popBackStack();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}