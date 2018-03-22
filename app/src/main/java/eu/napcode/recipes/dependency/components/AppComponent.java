package eu.napcode.recipes.dependency.components;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import eu.napcode.recipes.RecipesApp;
import eu.napcode.recipes.dependency.modules.ActivityModule;
import eu.napcode.recipes.dependency.modules.AppModule;
import eu.napcode.recipes.dependency.modules.FragmentModule;
import eu.napcode.recipes.dependency.modules.RepositoryModule;
import eu.napcode.recipes.dependency.modules.RxModule;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityModule.class,
        AppModule.class,
        ViewModelModule.class,
        RepositoryModule.class,
        RxModule.class,
        FragmentModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(RecipesApp application);

        AppComponent build();
    }

    void inject(RecipesApp application);
}
