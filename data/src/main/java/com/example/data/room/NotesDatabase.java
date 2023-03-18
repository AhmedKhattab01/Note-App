package com.example.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.domain.entity.Category;
import com.example.domain.entity.Note;

@Database(entities = {Note.class, Category.class}, version = 3, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    abstract public NoteDao getNoteDao();
    abstract public CategoryDao getCategoryDao();
}
