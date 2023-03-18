package com.example.simplenoteapp.utils.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.entity.Note;
import com.example.simplenoteapp.screens.notes.NotesFragmentDirections;
import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.ItemRvNotesBinding;
import com.example.simplenoteapp.screens.favourite.FavouriteFragmentDirections;
import com.example.simplenoteapp.utils.callback.OnFavouriteClickListener;
import com.example.simplenoteapp.utils.differs.NoteDiffItemCallback;

public class NotesAdapter extends ListAdapter<Note, NotesAdapter.ViewHolder> {
    private OnFavouriteClickListener onFavouriteClickListener;

    public NotesAdapter() {
        super(new NoteDiffItemCallback());
    }

    public void setOnFavouriteClickListener(OnFavouriteClickListener listener) {
        this.onFavouriteClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return new ViewHolder(ItemRvNotesBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));

        holder.binding.imageButton.setOnClickListener(view -> {
            onFavouriteClickListener.onFavouriteClicked(getItem(position));
        });

        holder.binding.getRoot().setOnClickListener(view -> {
            if (Navigation.findNavController(view).getCurrentDestination().getId() == R.id.notesFragment) {
                Navigation.findNavController(view).navigate(NotesFragmentDirections.actionNotesFragmentToNoteDetailsFragment(getItem(position)));
            } else {
                Navigation.findNavController(view).navigate(FavouriteFragmentDirections.actionFavouriteFragmentToNoteFragment(getItem(position)));
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRvNotesBinding binding;

        public ViewHolder(@NonNull ItemRvNotesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Note note) {
            binding.setNote(note);
            if (note.isFavourite()) {
                binding.imageButton.setImageResource(R.drawable.baseline_favorite_24);
            } else {
                binding.imageButton.setImageResource(R.drawable.baseline_favorite_border_24);
            }

            binding.executePendingBindings();
        }

    }
}
