package eu.napcode.recipes.dependency.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import eu.napcode.recipes.step.StepFragment;

@Module
public interface FragmentModule {

    @ContributesAndroidInjector
    StepFragment bindStepFragment();
}
