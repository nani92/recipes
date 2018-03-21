package eu.napcode.recipes;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

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

import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.repository.RecipesRepository;
import io.reactivex.Flowable;

import static eu.napcode.recipes.Resource.Status.ERROR;
import static eu.napcode.recipes.Resource.Status.SUCCESS;


@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    RecipesRepository recipesRepository;

    private MainViewModel mainViewModel;
    private List<Recipe> recipes;

    @Mock
    private Observer<Resource<List<Recipe>>> recipeObserver;

    @Before
    public void initial() {
        mainViewModel = new MainViewModel(recipesRepository, new MockRxSchedulers());

        this.recipes = new ArrayList<>();
        this.recipes.add(new Recipe());
    }


    @Test
    public void isRepositoryConnected() {
        Assert.assertNotNull(mainViewModel.recipesRepository);
    }

    @Test
    public void testCallRepositoryGetRecipes() {
        Mockito.when(recipesRepository.getRecipes())
                .thenReturn(Flowable.fromArray(this.recipes));

        mainViewModel.getRecipes();

        Mockito.verify(recipesRepository).getRecipes();
    }

    @Test
    public void testReturnCorrectList() {
        Mockito.when(recipesRepository.getRecipes())
                .thenReturn(Flowable.fromArray(this.recipes));

        LiveData<Resource<List<Recipe>>> recipeLiveData = mainViewModel.getRecipes();
        recipeLiveData.observeForever(recipeObserver);

        Assert.assertEquals(SUCCESS, recipeLiveData.getValue().status);
        Assert.assertEquals(recipes, recipeLiveData.getValue().data);
    }

    @Test
    public void testReturnError() {
        Mockito.when(recipesRepository.getRecipes())
                .thenReturn(Flowable.error(new Throwable()));

        LiveData<Resource<List<Recipe>>> recipeLiveData = mainViewModel.getRecipes();
        recipeLiveData.observeForever(recipeObserver);

        Assert.assertEquals(ERROR, recipeLiveData.getValue().status);
    }
}