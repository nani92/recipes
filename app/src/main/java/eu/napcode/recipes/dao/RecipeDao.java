package eu.napcode.recipes.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
import static eu.napcode.recipes.dao.RecipeEntity.TABLE_NAME;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM " + TABLE_NAME)
    Flowable<List<RecipeEntity>> getAllRecipes();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE id = :id")
    Maybe<RecipeEntity> getRecipeById(long id);

    @Insert(onConflict = REPLACE)
    long addRecipe(RecipeEntity recipeEntity);
}
