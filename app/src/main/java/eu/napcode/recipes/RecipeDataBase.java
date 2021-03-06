package eu.napcode.recipes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import eu.napcode.recipes.dao.ingredients.IngredientDao;
import eu.napcode.recipes.dao.ingredients.IngredientEntity;
import eu.napcode.recipes.dao.recipe.RecipeDao;
import eu.napcode.recipes.dao.recipe.RecipeEntity;
import eu.napcode.recipes.dao.step.StepDao;
import eu.napcode.recipes.dao.step.StepEntity;

@Database(entities = {RecipeEntity.class, StepEntity.class, IngredientEntity.class}, version = 2)
public abstract class RecipeDataBase extends RoomDatabase{
    public static final String RECIPES_DATA_BASE_NAME = "recipe db";

    public abstract RecipeDao recipeDao();

    public abstract StepDao stepDao();

    public abstract IngredientDao ingredientDao();
}
