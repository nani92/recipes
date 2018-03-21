package eu.napcode.recipes.repository;

import java.util.List;

import eu.napcode.recipes.model.Recipe;
import io.reactivex.Flowable;

public interface RecipesRepository {

   Flowable<List<Recipe>> getRecipes();
}

