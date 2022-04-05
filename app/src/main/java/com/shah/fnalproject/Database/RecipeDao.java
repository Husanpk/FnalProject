package com.shah.fnalproject.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.shah.fnalproject.Database.Recipe;

import java.util.*;
@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Insert
    void insertAll(Recipe... recipes);

    @Query("DELETE FROM Recipe WHERE rid = :id")
    void deleteById(int id);
}
