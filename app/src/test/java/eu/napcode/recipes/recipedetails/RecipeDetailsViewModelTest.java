package eu.napcode.recipes.recipedetails;

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

import eu.napcode.recipes.MockRxSchedulers;
import eu.napcode.recipes.model.Step;
import eu.napcode.recipes.repository.Resource;
import eu.napcode.recipes.repository.recipes.RecipesRepository;
import io.reactivex.Flowable;

import static eu.napcode.recipes.repository.Resource.Status.SUCCESS;

@RunWith(MockitoJUnitRunner.class)
public class RecipeDetailsViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    RecipesRepository recipesRepository;

    private RecipeDetailsViewModel viewModel;
    private int id = 5;
    private List<Step> steps;

    @Mock
    private Observer<Resource<List<Step>>> stepsObserver;

    @Before
    public void initial() {
        viewModel = new RecipeDetailsViewModel(recipesRepository, new MockRxSchedulers());
        viewModel.setRecipeId(id);

        steps = new ArrayList<>();
        steps.add(new Step());
        Mockito.when(recipesRepository.getStepsForRecipe(Mockito.anyInt()))
                .thenReturn(Flowable.fromArray(steps));
    }

    @Test
    public void testSetRecipeId() {
        Assert.assertEquals(id, viewModel.recipeId);
    }

    @Test
    public void testGetSteps() {
        viewModel.getSteps().observeForever(stepsObserver);

        Mockito.verify(recipesRepository).getStepsForRecipe(id);
    }

    @Test
    public void testReturnSteps() {
        LiveData<Resource<List<Step>>> stepsResource  = viewModel.getSteps();
        stepsResource.observeForever(stepsObserver);
        stepsResource.observeForever(stepsObserver);

        Assert.assertEquals(SUCCESS, stepsResource.getValue().status);
        Assert.assertEquals(steps, stepsResource.getValue().data);
    }
}