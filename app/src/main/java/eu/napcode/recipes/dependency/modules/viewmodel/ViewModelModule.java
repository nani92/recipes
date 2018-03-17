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
import eu.napcode.recipes.MainViewModel;

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
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel mainViewModel(MainViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory viewModelFactory(ViewModelFactory factory);
}
