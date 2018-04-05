package eu.napcode.recipes.ui.ingredients;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import eu.napcode.recipes.model.Ingredient;
import eu.napcode.recipes.repository.recipes.RecipesRepository;

public class IngredientsViewModel extends ViewModel {

    private RecipesRepository recipesRepository;

    private int recipeId;

    private MutableLiveData<Ingredient> ingredientsMutableLiveData = new MutableLiveData<>();

    @Inject
    public IngredientsViewModel(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public void setRecipeInfo(int recipeId) {
        this.recipeId = recipeId;
    }

    public LiveData<Ingredient> getIngredients() {
        //ingredientsMutableLiveData.postValue(recipesRepository.getStepForRecipe(this.recipeId));

        return ingredientsMutableLiveData;
    }
}
