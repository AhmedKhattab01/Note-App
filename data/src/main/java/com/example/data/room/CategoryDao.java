package com.example.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.domain.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category_table")
    LiveData<List<Category>> categories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Update
    void updateCategory(Category category);
}
