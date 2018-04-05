package eu.napcode.recipes.dao.ingredients;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
import static eu.napcode.recipes.dao.ingredients.IngredientEntity.TABLE_NAME;

@Dao
public interface IngredientDao {

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE recipeId = :recipeId")
    Flowable<List<IngredientEntity>> getAllIngredientsForRecipe(long recipeId);

    @Insert(onConflict = REPLACE)
    long addIngredient(IngredientEntity ingredientEntity);

    @Query("DELETE FROM " + TABLE_NAME + " WHERE recipeId = :recipeId")
    int deleteAllForRecipe(int recipeId);
}
