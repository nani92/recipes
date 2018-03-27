package eu.napcode.recipes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import eu.napcode.recipes.dao.RecipeDao;
import eu.napcode.recipes.dao.RecipeEntity;

@Database(entities = {RecipeEntity.class}, version = 1)
public abstract class RecipeDataBase extends RoomDatabase{
    public static final String RECIPES_DATA_BASE_NAME = "recipe db";

    public abstract RecipeDao recipeDao();
}
