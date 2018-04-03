package eu.napcode.recipes.dao.step;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
import static eu.napcode.recipes.dao.step.StepEntity.TABLE_NAME;

@Dao
public interface StepDao {

    @Query("SELECT * FROM " + TABLE_NAME)
    Flowable<List<StepEntity>> getAllSteps();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE recipeId = :recipeId")
    Flowable<List<StepEntity>> getAllStepsForRecipe(long recipeId);

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE recipeId = :recipeId AND id = :id")
    Maybe<StepEntity> getStepFromRecipe(long recipeId, long id);

    @Insert(onConflict = REPLACE)
    long addStep(StepEntity stepEntity);
}
