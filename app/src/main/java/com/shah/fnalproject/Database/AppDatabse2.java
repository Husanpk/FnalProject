package com.shah.fnalproject.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Recipe2.class}, version = 2)
public abstract class AppDatabse2 extends RoomDatabase {
    public abstract RecipeDao2 recipeDao2();
}
