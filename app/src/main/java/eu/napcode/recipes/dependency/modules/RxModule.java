package eu.napcode.recipes.dependency.modules;

import dagger.Module;
import dagger.Provides;
import eu.napcode.recipes.rx.AppSchedulers;
import eu.napcode.recipes.rx.RxSchedulers;

@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppSchedulers();
    }
}
