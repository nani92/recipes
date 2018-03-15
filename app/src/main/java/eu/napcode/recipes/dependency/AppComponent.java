package eu.napcode.recipes.dependency;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import eu.napcode.recipes.RecipesApp;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class
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
