package eu.napcode.recipes.dependency.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import eu.napcode.recipes.ui.ingredients.IngredientsFragment;
import eu.napcode.recipes.ui.step.StepFragment;

@Module
public interface FragmentModule {

    @ContributesAndroidInjector
    StepFragment bindStepFragment();

    @ContributesAndroidInjector
    IngredientsFragment bindIngredientsFragment();
}
