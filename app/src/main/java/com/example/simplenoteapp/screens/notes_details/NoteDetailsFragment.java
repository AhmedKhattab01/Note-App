package com.example.simplenoteapp.screens.notes_details;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentNotesDetailsBinding;
import com.example.simplenoteapp.screens.NotesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NoteDetailsFragment extends Fragment implements MenuProvider {
    private NoteDetailsFragmentArgs args;
    private FragmentNotesDetailsBinding binding;
    private NotesViewModel myViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        args = NoteDetailsFragmentArgs.fromBundle(getArguments());

        myViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotesDetailsBinding.inflate(inflater, container, false);

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        binding.etTitle.setText(args.getNote().getTitle());
        binding.etNote.setText(args.getNote().getNoteText());
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_edit_fragment, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_menu_edit_note_save) {
            if (binding.etTitle.getText().toString().isEmpty() || binding.etNote.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.all_fields_required), Toast.LENGTH_SHORT).show();
            } else {
                args.getNote().setNoteText(binding.etNote.getText().toString());
                args.getNote().setTitle(binding.etTitle.getText().toString());

                new Thread(() -> {
                    myViewModel.updateNote(args.getNote());
                }).start();

                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        } else if (menuItem.getItemId() == R.id.action_menu_edit_note_delete) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle(R.string.note_deletion);
            alertDialog.setMessage(R.string.note_deletion_msg);
            alertDialog.setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss());
            alertDialog.setPositiveButton(getString(R.string.delete), (dialogInterface, i) -> {

                new Thread(() -> {
                    myViewModel.deleteNote(args.getNote());
                }).start();

                Navigation.findNavController(binding.getRoot()).popBackStack();
            }).create().show();
        }
        return true;
    }
}