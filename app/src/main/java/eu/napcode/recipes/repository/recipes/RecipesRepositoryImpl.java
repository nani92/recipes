package eu.napcode.recipes.repository.recipes;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import eu.napcode.recipes.api.RecipeService;
import eu.napcode.recipes.dao.ingredients.IngredientDao;
import eu.napcode.recipes.dao.ingredients.IngredientMapper;
import eu.napcode.recipes.dao.recipe.RecipeDao;
import eu.napcode.recipes.dao.recipe.RecipeMapper;
import eu.napcode.recipes.dao.step.StepDao;
import eu.napcode.recipes.dao.step.StepMapper;
import eu.napcode.recipes.model.Ingredient;
import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.model.Step;
import eu.napcode.recipes.rx.RxSchedulers;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Singleton
public class RecipesRepositoryImpl implements RecipesRepository {

    private final RxSchedulers rxSchedulers;
    private RecipeService recipeService;
    private RecipeDao recipeDao;
    private StepDao stepDao;
    private IngredientDao ingredientDao;

    private int recipeId;
    private List<Step> steps;

    @Inject
    public RecipesRepositoryImpl(RecipeService recipeService, RecipeDao recipeDao, StepDao stepDao, IngredientDao ingredientDao, RxSchedulers rxSchedulers) {
        this.recipeService = recipeService;
        this.recipeDao = recipeDao;
        this.stepDao = stepDao;
        this.ingredientDao = ingredientDao;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public Flowable<List<Recipe>> getRecipes() {
        Flowable<List<Recipe>> recipesFlowable = this.recipeService.getRecipes();
        recipesFlowable
                .subscribeOn(rxSchedulers.io())
                .subscribe(this::saveRecipes,
                        error -> {
                        });

        return recipesFlowable;
    }

    @Override
    public Maybe<Recipe> getRecipeById(int id) {
        return recipeDao.getRecipeById(id).map(RecipeMapper::toRecipe);
    }

    private void saveRecipes(List<Recipe> recipes) {

        for (Recipe recipe : recipes) {
            this.recipeDao.addRecipe(RecipeMapper.toEntity(recipe));

            if (recipe.getSteps() == null) {
                continue;
            }

            for (Step step : recipe.getSteps()) {
                this.stepDao.addStep(StepMapper.toStepEntity(step, recipe.getId()));
            }

            for (Ingredient ingredient: recipe.getIngredients()) {
                this.ingredientDao.addIngredient(IngredientMapper.toIngredientEntity(ingredient, recipe.getId()));
            }
        }
    }

    @Override
    public Flowable<List<Step>> getStepsForRecipe(int recipeId) {
        Flowable<List<Step>> listFlowable = stepDao.getAllStepsForRecipe(recipeId).map(StepMapper::toSteps);
        listFlowable.subscribeOn(this.rxSchedulers.io())
                .observeOn(this.rxSchedulers.androidMainThread())
                .subscribe(steps1 -> {
                    this.recipeId = recipeId;
                    this.steps = steps1;
                });

        return listFlowable;
    }

    @Override
    public boolean hasNextStepForRecipe(int recipeId, int stepId) {

        if (recipeId != this.recipeId) {
            return false;
        }

        return stepId + 1 < this.steps.size();
    }

    @Override
    public Step getStepForRecipe(int recipeId, int stepId) {

        if (recipeId != this.recipeId) {
            return null;
        }

        for (Step step: steps) {

            if (step.getId() == stepId) {
                return step;
            }
        }

        return null;
    }

    @Override
    public Flowable<List<Ingredient>> getIngredientsForRecipe(int recipeId) {
        return ingredientDao.getAllIngredientsForRecipe(recipeId).map(IngredientMapper::toIngredients);
    }
}

