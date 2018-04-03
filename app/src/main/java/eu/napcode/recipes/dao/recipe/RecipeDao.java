package eu.napcode.recipes.dao.recipe;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM " + RecipeEntity.TABLE_NAME)
    Flowable<List<RecipeEntity>> getAllRecipes();

    @Query("SELECT * FROM " + RecipeEntity.TABLE_NAME + " WHERE id = :id")
    Maybe<RecipeEntity> getRecipeById(long id);

    @Insert(onConflict = REPLACE)
    long addRecipe(RecipeEntity recipeEntity);
}
