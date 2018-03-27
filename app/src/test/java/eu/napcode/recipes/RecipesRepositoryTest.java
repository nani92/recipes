package eu.napcode.recipes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import eu.napcode.recipes.api.RecipeService;
import eu.napcode.recipes.dao.RecipeDao;
import eu.napcode.recipes.dao.RecipeEntity;
import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.repository.recipes.RecipesRepository;
import eu.napcode.recipes.repository.recipes.RecipesRepositoryImpl;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(MockitoJUnitRunner.class)
public class RecipesRepositoryTest {

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeDao recipeDao;

    private RecipesRepository recipesRepository;

    @Before
    public void initial() {
        recipesRepository = new RecipesRepositoryImpl(recipeService, recipeDao);
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
    public void testGetRecipeById() {
        Mockito.when(recipeDao.getRecipeById(Mockito.anyInt()))
                .thenReturn(Maybe.just(new RecipeEntity()));
        int id = 0;

        recipesRepository.getRecipeById(id);

        Mockito.verify(recipeDao).getRecipeById(id);
    }

    @Test
    public void testReturnRecipeByIdEmpty() {
        Mockito.when(recipeDao.getRecipeById(Mockito.anyInt()))
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

        recipeSubscriber.assertValue(recipe ->
                recipeEntity.getId() != recipeEntity.getId());
    }
}
