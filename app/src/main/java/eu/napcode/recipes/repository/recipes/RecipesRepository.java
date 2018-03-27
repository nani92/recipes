package eu.napcode.recipes.repository.recipes;

import java.util.List;

import eu.napcode.recipes.model.Recipe;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface RecipesRepository {

   Flowable<List<Recipe>> getRecipes();

   Maybe<Recipe> getRecipeById(int id);
}

