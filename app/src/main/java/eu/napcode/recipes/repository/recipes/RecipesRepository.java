package eu.napcode.recipes.repository.recipes;

import java.util.List;

import eu.napcode.recipes.model.Ingredient;
import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.model.Step;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface RecipesRepository {

   Flowable<List<Recipe>> getRecipes();

   Maybe<Recipe> getRecipeById(int id);

   Flowable<List<Step>> getStepsForRecipe(int recipeId);

   boolean hasNextStepForRecipe(int recipeId, int stepId);

   Step getStepForRecipe(int recipeId, int stepId);

   Flowable<List<Ingredient>> getIngredientsForRecipe(int recipeId);
}

