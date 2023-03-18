package com.example.data.repo;

import androidx.lifecycle.LiveData;

import com.example.data.room.CategoryDao;
import com.example.data.room.NoteDao;
import com.example.domain.entity.Category;
import com.example.domain.entity.Note;
import com.example.domain.repo.LocalRepository;

import java.util.List;

public class LocalRepoImpl implements LocalRepository {

    NoteDao noteDao;
    CategoryDao categoryDao;

    public LocalRepoImpl(NoteDao noteDao, CategoryDao categoryDao) {
        this.noteDao = noteDao;
        this.categoryDao = categoryDao;
    }


    @Override
    public LiveData<List<Note>> getAllNotes() {
        return noteDao.getAllNotes();
    }

    @Override
    public LiveData<List<Note>> getNotesByCategoryAndFavourite(String category) {
        return noteDao.getNotesByCategoryAndFavourite(category);
    }

    @Override
    public LiveData<List<Note>> getNotesByCategory(String category) {
        return noteDao.getNotesByCategory(category);
    }

    @Override
    public void insertNote(Note note) {
        noteDao.insertNote(note);
    }

    @Override
    public void updateNote(Note note) {
        noteDao.updateNote(note);
    }

    @Override
    public void deleteAllNotes() {
        noteDao.deleteAllNotes();
    }

    @Override
    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }

    @Override
    public LiveData<List<Category>> getAllCategories() {
        return categoryDao.categories();
    }

    @Override
    public LiveData<List<Category>> categories() {
        return categoryDao.categories();
    }

    @Override
    public void insertCategory(Category category) {
        categoryDao.insertCategory(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryDao.deleteCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.updateCategory(category);
    }

    @Override
    public LiveData<List<Note>> getNotesByFavourite() {
        return noteDao.getNotesByFavourite();
    }

    @Override
    public void deleteNotesByCategory(String category) {
        noteDao.deleteNotesByCategory(category);
    }
}
