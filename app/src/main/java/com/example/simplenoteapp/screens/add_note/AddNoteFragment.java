package com.example.simplenoteapp.screens.add_note;

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

import com.example.domain.entity.Note;
import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentAddNoteBinding;
import com.example.simplenoteapp.screens.NotesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddNoteFragment extends Fragment {
    private FragmentAddNoteBinding binding;
    private NotesViewModel myViewModel;

    private AddNoteFragmentArgs args;

    Note note;
    String title;
    String text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        args = AddNoteFragmentArgs.fromBundle(getArguments());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false);

        myViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {

        });

        binding.btnCancel.setOnClickListener(view -> Navigation.findNavController(view).popBackStack());

        binding.btnSave.setOnClickListener(view -> {
            setNote(args.getCategoryObj().getTitle());
            if (title.isEmpty() || text.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.all_fields_required), Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> myViewModel.insertNote(note)).start();

            Navigation.findNavController(view).popBackStack();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    void setNote(String category) {
        title = binding.etTitle.getText().toString();
        text = binding.etText.getText().toString();

        note = category.equals(getString(R.string.select_category)) ? new Note(0,title,text,null) : new Note(0,title,text,category);
    }
}