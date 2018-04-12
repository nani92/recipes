package eu.napcode.recipes;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import eu.napcode.recipes.api.RecipeService;
import eu.napcode.recipes.dao.ingredients.IngredientDao;
import eu.napcode.recipes.dao.recipe.RecipeDao;
import eu.napcode.recipes.dao.recipe.RecipeEntity;
import eu.napcode.recipes.dao.step.StepDao;
import eu.napcode.recipes.dao.step.StepEntity;
import eu.napcode.recipes.model.Ingredient;
import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.model.Step;
import eu.napcode.recipes.repository.recipes.RecipesRepository;
import eu.napcode.recipes.repository.recipes.RecipesRepositoryImpl;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(MockitoJUnitRunner.class)
public class RecipesRepositoryTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeDao recipeDao;

    @Mock
    StepDao stepDao;

    @Mock
    IngredientDao ingredientDao;

    private RecipesRepository recipesRepository;

    @Before
    public void initial() {
        recipesRepository = new RecipesRepositoryImpl(recipeService, recipeDao, stepDao, ingredientDao, new MockRxSchedulers());
    }

    @Test
    public void testDownloadList() {
        List<Recipe> recipes = new ArrayList<>();
        Mockito.when(recipeService.getRecipes())
                .thenReturn(Flowable.fromArray(recipes));

        recipesRepository.getRecipes();

        Mockito.verify(recipeService).getRecipes();
    }

    @Test
    public void testReturnDownloadedList() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());
        Mockito.when(recipeService.getRecipes())
                .thenReturn(Flowable.fromArray(recipes));

        TestSubscriber<List<Recipe>> recipesSubscriber = new TestSubscriber<>();
        recipesRepository.getRecipes().subscribe(recipesSubscriber);

        recipesSubscriber.assertValue(recipes);
    }

    @Test
    public void testSavingRecipesToDatabase() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());
        Mockito.when(recipeService.getRecipes())
                .thenReturn(Flowable.fromArray(recipes));
        Mockito.when(recipeDao.addRecipe(Mockito.any()))
                .thenReturn(0l);

        recipesRepository.getRecipes();

        Mockito.verify(recipeDao, Mockito.after(100).times(recipes.size()))
                .addRecipe(Mockito.any());
    }

    @Test
    public void testSavingStepsToDatabase() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(getRecipesWithSteps());
        Mockito.when(recipeService.getRecipes())
                .thenReturn(Flowable.fromArray(recipes));
        Mockito.when(recipeDao.addRecipe(Mockito.any()))
                .thenReturn(0l);
        Mockito.when(stepDao.addStep(Mockito.any()))
                .thenReturn(0l);

        recipesRepository.getRecipes();

        Mockito.verify(stepDao, Mockito.after(100).times(recipes.get(0).getSteps().size()))
                .addStep(Mockito.any());
    }

    private Recipe getRecipesWithSteps() {
        Recipe recipe = new Recipe();
        List<Step> steps = new ArrayList<>();
        steps.add(new Step());
        steps.add(new Step());
        steps.add(new Step());
        recipe.setSteps(steps);

        return recipe;
    }

    @Test
    public void testGetRecipeById() {
        Mockito.when(recipeDao.getRecipeById(Mockito.anyLong()))
                .thenReturn(Maybe.just(new RecipeEntity()));
        int id = 0;

        recipesRepository.getRecipeById(id);

        Mockito.verify(recipeDao).getRecipeById(id);
    }

    @Test
    public void testReturnRecipeByIdEmpty() {
        Mockito.when(recipeDao.getRecipeById(Mockito.anyLong()))
                .thenReturn(Maybe.empty());

        TestSubscriber<Recipe> recipeSubscriber = new TestSubscriber<>();
        recipesRepository.getRecipeById(0).toFlowable().subscribe(recipeSubscriber);

        recipeSubscriber.assertSubscribed();
    }

    @Test
    public void testReturnRecipeById() {
        RecipeEntity recipeEntity = new RecipeEntity();
        int id = 3;
        recipeEntity.setId(id);
        Mockito.when(recipeDao.getRecipeById(Mockito.anyLong()))
                .thenReturn(Maybe.just(recipeEntity));

        TestSubscriber<Recipe> recipeSubscriber = new TestSubscriber<>();
        recipesRepository.getRecipeById(0).toFlowable().subscribe(recipeSubscriber);

        recipeSubscriber.assertValue(
                recipe -> recipeEntity.getId() == recipeEntity.getId());
    }

    @Test
    public void testGetStepsByRecipeId() {
        Mockito.when(stepDao.getAllStepsForRecipe(Mockito.anyLong()))
                .thenReturn(Flowable.empty());
        int id = 0;

        recipesRepository.getStepsForRecipe(id);

        Mockito.verify(stepDao).getAllStepsForRecipe(id);
    }

    @Test
    public void testReturnStepsByRecipeId() {
        List<StepEntity> steps = getStepEntities();
        Mockito.when(stepDao.getAllStepsForRecipe(Mockito.anyLong()))
                .thenReturn(Flowable.fromArray(steps));
        int id = 0;

        TestSubscriber<List<Step>> stepsSubscriber = new TestSubscriber<>();
        recipesRepository.getStepsForRecipe(id).subscribe(stepsSubscriber);

        stepsSubscriber.assertValue(steps1 -> steps1.size() == steps.size());
    }

    @Test
    public void testReturnInfoForMoreStepsInRecipe() {
        List<StepEntity> steps = getStepEntities();
        Mockito.when(stepDao.getAllStepsForRecipe(Mockito.anyLong()))
                .thenReturn(Flowable.fromArray(steps));
        int id = 0;

        recipesRepository.getStepsForRecipe(id);
        boolean shouldHasNext = recipesRepository.hasNextStepForRecipe(id, 0);
        boolean shouldNotHasNext = recipesRepository.hasNextStepForRecipe(id, 1);

        Assert.assertEquals(true, shouldHasNext);
        Assert.assertEquals(false, shouldNotHasNext);
    }

    @Test
    public void testReturnStep() {
        List<StepEntity> steps = getStepEntities();
        Mockito.when(stepDao.getAllStepsForRecipe(Mockito.anyLong()))
                .thenReturn(Flowable.fromArray(steps));
        int id = 0;

        recipesRepository.getStepsForRecipe(id);

        Step shouldBeStep = recipesRepository.getStepForRecipe(id, 0);
        Step shouldNotBeStep = recipesRepository.getStepForRecipe(id, 2);

        Assert.assertNotNull(shouldBeStep);
        Assert.assertNull(shouldNotBeStep);
    }

    public List<StepEntity> getStepEntities() {
        List<StepEntity> steps = new ArrayList<>();
        steps.add(getStepEntityWithId(0));
        steps.add(getStepEntityWithId(1));

        return steps;
    }

    private StepEntity getStepEntityWithId(int id) {
        StepEntity stepEntity = new StepEntity();
        stepEntity.setId(id);

        return stepEntity;
    }
}
