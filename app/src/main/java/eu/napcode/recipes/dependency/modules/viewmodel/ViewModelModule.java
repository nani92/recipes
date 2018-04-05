package eu.napcode.recipes.dependency.modules.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;
import eu.napcode.recipes.ui.recipedetails.RecipeDetailsViewModel;
import eu.napcode.recipes.ui.recipes.RecipesViewModel;
import eu.napcode.recipes.ui.step.StepViewModel;

@Module
public interface ViewModelModule {

    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @IntoMap
    @Binds
    @ViewModelKey(RecipesViewModel.class)
    abstract ViewModel mainViewModel(RecipesViewModel viewModel);

    @IntoMap
    @Binds
    @ViewModelKey(StepViewModel.class)
    abstract ViewModel stepViewModel(StepViewModel viewModel);

    @IntoMap
    @Binds
    @ViewModelKey(RecipeDetailsViewModel.class)
    abstract ViewModel recipeDetailsViewModel(RecipeDetailsViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory viewModelFactory(ViewModelFactory factory);
}
