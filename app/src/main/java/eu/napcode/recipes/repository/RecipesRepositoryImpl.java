package eu.napcode.recipes.repository;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import eu.napcode.recipes.api.RecipeService;
import eu.napcode.recipes.model.Recipe;
import io.reactivex.Flowable;

public class RecipesRepositoryImpl implements RecipesRepository {

    private RecipeService recipeService;

    @Inject
    public RecipesRepositoryImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public Flowable<List<Recipe>> getRecipes() {
        return recipeService.getRecipes();
    }
}

