package com.example.simplenoteapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.simplenoteapp.databinding.FragmentCategoryEditBinding;
import com.example.simplenoteapp.screens.NotesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoryEditFragment extends Fragment {
    private FragmentCategoryEditBinding binding;

    CategoryEditFragmentArgs args;
    private NotesViewModel myViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = CategoryEditFragmentArgs.fromBundle(getArguments());

        myViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCategoryEditBinding.inflate(inflater, container, false);

        binding.btnDeleteCategory.setOnClickListener(view -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle(getString(R.string.category_deletion));
            alertDialog.setMessage(getString(R.string.category_deletion_msg));
            alertDialog.setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss());
            alertDialog.setPositiveButton(getString(R.string.delete), (dialogInterface, i) -> {

                new Thread(() -> {
                    myViewModel.deleteNotesByCategory(args.getCategoryObj().getTitle());
                    myViewModel.deleteCategory(args.getCategoryObj());
                }).start();

                Navigation.findNavController(view).navigate(R.id.action_categoryEditFragment_to_homeFragment);

            });

            alertDialog.create().show();
        });

        binding.etName.setText(args.getCategoryObj().getTitle());

        binding.btnCancel.setOnClickListener(view -> {
            Navigation.findNavController(view).popBackStack();

        });

        binding.btnSaveEdit.setOnClickListener(view -> {
            if (binding.etName.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.all_fields_required), Toast.LENGTH_SHORT).show();
            } else {
                args.getCategoryObj().setTitle(binding.etName.getText().toString());

                new Thread(() -> {
                    myViewModel.updateCategory(args.getCategoryObj());
                }).start();

                Navigation.findNavController(view).popBackStack();
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}