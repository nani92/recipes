package eu.napcode.recipes.repository.recipes;

import java.util.List;

import javax.inject.Inject;

import eu.napcode.recipes.api.RecipeService;
import eu.napcode.recipes.dao.recipe.RecipeDao;
import eu.napcode.recipes.dao.recipe.RecipeMapper;
import eu.napcode.recipes.dao.step.StepDao;
import eu.napcode.recipes.dao.step.StepMapper;
import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.model.Step;
import eu.napcode.recipes.rx.RxSchedulers;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public class RecipesRepositoryImpl implements RecipesRepository {

    private final RxSchedulers rxSchedulers;
    private RecipeService recipeService;
    private RecipeDao recipeDao;
    private StepDao stepDao;

    @Inject
    public RecipesRepositoryImpl(RecipeService recipeService, RecipeDao recipeDao, StepDao stepDao, RxSchedulers rxSchedulers) {
        this.recipeService = recipeService;
        this.recipeDao = recipeDao;
        this.stepDao = stepDao;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public Flowable<List<Recipe>> getRecipes() {
        Flowable<List<Recipe>> recipesFlowable = this.recipeService.getRecipes();
        recipesFlowable
                .subscribeOn(rxSchedulers.io())
                .subscribe(this::saveRecipes,
                error -> {});

        return recipesFlowable;
    }

    @Override
    public Maybe<Recipe> getRecipeById(int id) {
        return recipeDao.getRecipeById(id).map(RecipeMapper::toRecipe);
    }

    private void saveRecipes(List<Recipe> recipes) {

        for (Recipe recipe: recipes) {
            this.recipeDao.addRecipe(RecipeMapper.toEntity(recipe));

            if (recipe.getSteps() == null) {
                continue;
            }

            for (Step step: recipe.getSteps()) {
                this.stepDao.addStep(StepMapper.toStepEntity(step, recipe.getId()));
            }
        }
    }

    @Override
    public Flowable<List<Step>> getStepsForRecipe(int recipeId) {
        return stepDao.getAllStepsForRecipe(recipeId).map(StepMapper::toSteps);
    }
}

