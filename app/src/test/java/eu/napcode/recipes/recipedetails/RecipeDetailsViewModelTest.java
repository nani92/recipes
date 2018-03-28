package eu.napcode.recipes.recipedetails;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import eu.napcode.recipes.MockRxSchedulers;
import eu.napcode.recipes.repository.recipes.RecipesRepository;

@RunWith(MockitoJUnitRunner.class)
public class RecipeDetailsViewModelTest {

    @Mock
    RecipesRepository recipesRepository;

    private RecipeDetailsViewModel viewModel;

    @Before
    public void initial() {
        viewModel = new RecipeDetailsViewModel(recipesRepository, new MockRxSchedulers());
    }

    @Test
    public void testSetRecipeId() {
        int id = 5;
        viewModel.setRecipeId(id);

        Assert.assertEquals(id, viewModel.recipeId);
    }

    //@Test
}