package com.example.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.domain.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAllNotes();


    @Query("SELECT * FROM note_table WHERE category = :category")
    LiveData<List<Note>> getNotesByCategory(String category);

    @Query("SELECT * FROM note_table WHERE category = :category AND isFavourite = 1")
    LiveData<List<Note>> getNotesByCategoryAndFavourite(String category);

    @Query("SELECT * FROM note_table WHERE isFavourite = 1")
    LiveData<List<Note>> getNotesByFavourite();

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM note_table WHERE category = :category")
    void deleteNotesByCategory(String category);
}
