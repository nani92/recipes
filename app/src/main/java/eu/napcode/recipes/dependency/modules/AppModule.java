package eu.napcode.recipes.dependency.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.napcode.recipes.api.ApiUtils;
import eu.napcode.recipes.api.RecipeService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    RecipeService provideRecipeService() {
        return new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RecipeService.class);
    }
}
