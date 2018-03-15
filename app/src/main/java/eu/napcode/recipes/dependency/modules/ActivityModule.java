package eu.napcode.recipes.dependency.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import eu.napcode.recipes.MainActivity;

@Module
public interface ActivityModule {

    @ContributesAndroidInjector
    MainActivity bindMainActivity();
}
