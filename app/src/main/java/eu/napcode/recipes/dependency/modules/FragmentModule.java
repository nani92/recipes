package eu.napcode.recipes.dependency.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import eu.napcode.recipes.recipedetails.RecipeDetailsFragment;

@Module
public interface FragmentModule {

    @ContributesAndroidInjector
    RecipeDetailsFragment bindRecipeDetailsFragment();
}
