package eu.napcode.recipes.dependency.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import eu.napcode.recipes.recipedetails.RecipeDetailsActivity;
import eu.napcode.recipes.recipes.RecipesActivity;
import eu.napcode.recipes.step.StepActivity;

@Module
public interface ActivityModule {

    @ContributesAndroidInjector
    RecipesActivity bindMainActivity();

    @ContributesAndroidInjector
    RecipeDetailsActivity bindDetailsActivity();

    @ContributesAndroidInjector
    StepActivity bindStepActivity();
}
