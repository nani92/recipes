package eu.napcode.recipes.dependency.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import eu.napcode.recipes.recipedetails.RecipeDetailsActivity;
import eu.napcode.recipes.recipes.RecipesActivity;

@Module
public interface ActivityModule {

    @ContributesAndroidInjector
    RecipesActivity bindMainActivity();

    @ContributesAndroidInjector
    RecipeDetailsActivity bindDetailsActivity();
}
