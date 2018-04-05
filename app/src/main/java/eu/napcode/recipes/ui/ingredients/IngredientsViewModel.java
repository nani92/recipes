package eu.napcode.recipes.ui.ingredients;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import eu.napcode.recipes.model.Ingredient;
import eu.napcode.recipes.repository.recipes.RecipesRepository;
import eu.napcode.recipes.rx.RxSchedulers;

public class IngredientsViewModel extends ViewModel {

    private final RxSchedulers rxSchedulers;
    private RecipesRepository recipesRepository;

    private int recipeId;

    private MutableLiveData<List<Ingredient>> ingredientsMutableLiveData = new MutableLiveData<>();

    @Inject
    public IngredientsViewModel(RecipesRepository recipesRepository, RxSchedulers rxSchedulers) {
        this.recipesRepository = recipesRepository;
        this.rxSchedulers = rxSchedulers;
    }

    public void setRecipeInfo(int recipeId) {
        this.recipeId = recipeId;
    }

    public LiveData<List<Ingredient>> getIngredients() {
        recipesRepository.getIngredientsForRecipe(this.recipeId)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidMainThread())
                .subscribe(ingredients -> ingredientsMutableLiveData.postValue(ingredients));

        return ingredientsMutableLiveData;
    }
}
