package eu.napcode.recipes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.repository.RecipesRepository;
import eu.napcode.recipes.rx.RxSchedulers;

public class MainViewModel extends ViewModel {

    RecipesRepository recipesRepository;
    private final MutableLiveData<Resource<List<Recipe>>> recipes = new MutableLiveData<>();
    private RxSchedulers rxSchedulers;

    @Inject
    public MainViewModel(RecipesRepository recipesRepository, RxSchedulers rxSchedulers) {
        this.recipesRepository = recipesRepository;
        this.rxSchedulers = rxSchedulers;
    }

    public LiveData<Resource<List<Recipe>>> getRecipes() {
        recipesRepository
                .getRecipes()
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidMainThread())
                .doOnSubscribe(it -> recipes.setValue(Resource.loading(null)))
                .subscribe(recipes -> this.recipes.setValue(Resource.success(recipes)),
                        error -> this.recipes.setValue(Resource.error(error)));

        return recipes;
    }
}
