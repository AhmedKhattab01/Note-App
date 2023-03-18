package com.example.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.data.room.CategoryDao;
import com.example.data.room.NoteDao;
import com.example.data.room.NotesDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LocalModule {
    @Provides
    @Singleton
    NoteDao provideNoteDao(NotesDatabase notesDatabase) {
        return notesDatabase.getNoteDao();
    }

    @Provides
    @Singleton
    CategoryDao provideCategoryDao(NotesDatabase notesDatabase) {
        return notesDatabase.getCategoryDao();
    }

    @Provides
    @Singleton
    NotesDatabase provideNotesDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,NotesDatabase.class,"notes_database").fallbackToDestructiveMigration().build();
    }
}
