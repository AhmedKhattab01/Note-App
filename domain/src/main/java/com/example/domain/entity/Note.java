package com.example.domain.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String noteText;
    private String category;
    private boolean isFavourite = false;

    public Note(int id, String title, String noteText, String category) {
        this.id = id;
        this.title = title;
        this.noteText = noteText;
        this.category = category;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        noteText = in.readString();
        category = in.readString();
        isFavourite = in.readBoolean();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(noteText);
        parcel.writeBoolean(isFavourite);
        parcel.writeString(category);
    }
}
