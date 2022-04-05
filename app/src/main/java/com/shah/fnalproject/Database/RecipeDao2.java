package com.shah.fnalproject.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDao2 {

    @Query("SELECT * FROM recipe2")
    List<Recipe2> getAll2();

    @Insert
    void insertAll2(Recipe2... recipes2);

    @Query("DELETE FROM Recipe2 WHERE id = :id")
    void deleteById2(String id);
}
