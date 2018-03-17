package eu.napcode.recipes;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import eu.napcode.recipes.repository.RecipesRepository;

public class MainViewModel extends ViewModel {

    RecipesRepository recipesRepository;

    @Inject
    public MainViewModel(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }
}
