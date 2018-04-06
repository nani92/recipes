package eu.napcode.recipes.dependency.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import eu.napcode.recipes.ui.recipedetails.RecipeDetailsActivity;
import eu.napcode.recipes.ui.recipes.RecipesActivity;
import eu.napcode.recipes.ui.step.StepActivity;
import eu.napcode.recipes.ui.widget.ChooseRecipeToWidgetActivity;

@Module
public interface ActivityModule {

    @ContributesAndroidInjector
    RecipesActivity bindMainActivity();

    @ContributesAndroidInjector
    RecipeDetailsActivity bindDetailsActivity();

    @ContributesAndroidInjector
    StepActivity bindStepActivity();

    @ContributesAndroidInjector
    ChooseRecipeToWidgetActivity bindChooseRecipeToWidgetActivity();
}
