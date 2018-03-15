package eu.napcode.recipes.dependency.components;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import eu.napcode.recipes.RecipesApp;
import eu.napcode.recipes.dependency.modules.ActivityModule;
import eu.napcode.recipes.dependency.modules.AppModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityModule.class,
        AppModule.class
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
