package com.example.simplenoteapp.screens.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentNotesBinding;
import com.example.simplenoteapp.screens.NotesViewModel;
import com.example.simplenoteapp.utils.adapters.NotesAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotesFragment extends Fragment implements MenuProvider {
    private FragmentNotesBinding binding;
    private NotesViewModel myViewModel;
    private NotesFragmentArgs args;
    private NotesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = NotesFragmentArgs.fromBundle(getArguments());

        myViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater, container, false);

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        setupRecyclerView();
        onRecyclerViewScroll();
        onFabClick();

        // Notes observer
        myViewModel.getNotesByCategory(args.getCategoryObj().getTitle()).observe(getViewLifecycleOwner(), notes -> {
            binding.tvCount.setText(notes.size() + " " + getString(R.string.note));
            if (!notes.isEmpty()) {
                binding.lottieAnimationView.setVisibility(View.GONE);
                adapter.submitList(notes);
            } else {
                binding.lottieAnimationView.setVisibility(View.VISIBLE);
            }
        });

        return binding.getRoot();
    }

    // initialize adapter
    // implement on fav click listener
    // set adapter
    private void setupRecyclerView() {
        adapter = new NotesAdapter();

        adapter.setOnFavouriteClickListener(note -> {
            note.setFavourite(!note.isFavourite());
            new Thread(() -> myViewModel.updateNote(note)).start();
        });

        binding.rvNotes.setAdapter(adapter);
    }

    // Navigate to add item fragment
    private void onFabClick() {
        binding.floatingActionButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(NotesFragmentDirections.actionNotesFragmentToAddNoteFragment(args.getCategoryObj())));
    }

    // Hide fab on scroll
    private void onRecyclerViewScroll() {
        binding.rvNotes.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_category,menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_category_edit) {
            Navigation.findNavController(binding.getRoot()).navigate(NotesFragmentDirections.actionNotesFragmentToCategoryEditFragment(args.getCategoryObj()));
        }
        return true;
    }
}