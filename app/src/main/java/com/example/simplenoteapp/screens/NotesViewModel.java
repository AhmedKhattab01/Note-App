package com.example.simplenoteapp.screens;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.entity.Category;
import com.example.domain.entity.Note;
import com.example.domain.repo.LocalRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NotesViewModel extends ViewModel {
    private LocalRepository localRepository;
    @Inject NotesViewModel(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    public LiveData<List<Note>> getAllNotes() {
        return localRepository.getAllNotes();
    }
    public LiveData<List<Category>> getAllCategories() {
        return localRepository.getAllCategories();
    }

    public void insertNote(Note note) {
        localRepository.insertNote(note);
    }

    public void updateNote(Note note) {
        localRepository.updateNote(note);
    }

    public void deleteAllNotes() {
        localRepository.deleteAllNotes();
    }

    public void deleteNote(Note note) {
        localRepository.deleteNote(note);
    }

    public void insertCategory(Category category) {
        localRepository.insertCategory(category);
    }

    public void updateCategory(Category category) {
        localRepository.updateCategory(category);
    }

    public void deleteCategory(Category category) {
        localRepository.deleteCategory(category);
    }

    public LiveData<List<Note>> getAllNotesByCategoryAndFavourite(String category) {
        return localRepository.getNotesByCategoryAndFavourite(category);
    }

    public LiveData<List<Note>> getAllNotesByFavourite() {
        return localRepository.getNotesByFavourite();
    }

    public LiveData<List<Note>> getNotesByCategory(String category) {
        return localRepository.getNotesByCategory(category);
    }

    public void deleteNotesByCategory(String category) {
        localRepository.deleteNotesByCategory(category);
    }
}
