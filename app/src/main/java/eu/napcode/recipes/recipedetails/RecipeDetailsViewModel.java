package eu.napcode.recipes.recipedetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.model.Step;
import eu.napcode.recipes.repository.Resource;
import eu.napcode.recipes.repository.recipes.RecipesRepository;
import eu.napcode.recipes.rx.RxSchedulers;

public class RecipeDetailsViewModel extends ViewModel {

    private RecipesRepository recipesRepository;
    private RxSchedulers rxSchedulers;

    int recipeId;
    private final MutableLiveData<Resource<List<Step>>> steps = new MutableLiveData<>();

    @Inject
    public RecipeDetailsViewModel(RecipesRepository recipesRepository, RxSchedulers rxSchedulers) {
        this.recipesRepository = recipesRepository;
        this.rxSchedulers = rxSchedulers;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public LiveData<Resource<List<Step>>> getSteps() {


        return steps;
    }
}
