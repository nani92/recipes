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
import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.repository.recipes.RecipesRepository;
import eu.napcode.recipes.repository.recipes.RecipesRepositoryImpl;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(MockitoJUnitRunner.class)
public class RecipesRepositoryTest {

    @Mock
    RecipeService recipeService;

    private RecipesRepository recipesRepository;

    @Before
    public void initial() {
        recipesRepository = new RecipesRepositoryImpl(recipeService);
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
}
