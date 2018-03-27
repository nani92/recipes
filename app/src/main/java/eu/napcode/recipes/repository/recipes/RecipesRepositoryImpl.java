package eu.napcode.recipes.repository.recipes;

import java.util.List;

import javax.inject.Inject;

import eu.napcode.recipes.api.RecipeService;
import eu.napcode.recipes.dao.RecipeDao;
import eu.napcode.recipes.dao.RecipeEntity;
import eu.napcode.recipes.dao.RecipeMapper;
import eu.napcode.recipes.model.Recipe;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public class RecipesRepositoryImpl implements RecipesRepository {

    private RecipeService recipeService;
    private RecipeDao recipeDao;

    @Inject
    public RecipesRepositoryImpl(RecipeService recipeService, RecipeDao recipeDao) {
        this.recipeService = recipeService;
        this.recipeDao = recipeDao;
    }

    @Override
    public Flowable<List<Recipe>> getRecipes() {
        Flowable<List<Recipe>> recipesFlowable = this.recipeService.getRecipes();
        recipesFlowable.subscribe(recipes -> saveRecipes(recipes));

        return recipesFlowable;
    }

    @Override
    public Maybe<Recipe> getRecipeById(int id) {
        return recipeDao.getRecipeById(id).map(RecipeMapper::toRecipe);
    }

    private void saveRecipes(List<Recipe> recipes) {

        for (Recipe recipe: recipes) {
            this.recipeDao.addRecipe(RecipeMapper.toEntity(recipe));
        }
    }
}

