package com.example.domain.repo;

import androidx.lifecycle.LiveData;

import com.example.domain.entity.Category;
import com.example.domain.entity.Note;

import java.util.List;

public interface LocalRepository {
    LiveData<List<Note>> getAllNotes();
    LiveData<List<Note>> getNotesByCategoryAndFavourite(String category);

    LiveData<List<Note>> getNotesByCategory(String category);
    void insertNote(Note note);

    void updateNote(Note note);

    void deleteAllNotes();

    void deleteNote(Note note);

    LiveData<List<Category>> getAllCategories();

    LiveData<List<Category>> categories();

    void insertCategory(Category category);

    void deleteCategory(Category category);

    void updateCategory(Category category);

    LiveData<List<Note>> getNotesByFavourite();

    void deleteNotesByCategory(String category);
}
