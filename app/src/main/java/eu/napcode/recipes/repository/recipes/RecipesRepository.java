package eu.napcode.recipes.repository.recipes;

import java.util.List;

import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.model.Step;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface RecipesRepository {

   Flowable<List<Recipe>> getRecipes();

   Maybe<Recipe> getRecipeById(int id);

   Flowable<List<Step>> getStepsForRecipe(int recipeId);

   boolean hasNextStepForRecipe(int recipeId, int stepId);

   Maybe<Step> getNextStepForRecipe(int recipeId, int stepId);
}

