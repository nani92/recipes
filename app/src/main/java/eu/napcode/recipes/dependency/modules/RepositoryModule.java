package eu.napcode.recipes.dependency.modules;

import dagger.Binds;
import dagger.Module;
import eu.napcode.recipes.repository.RecipesRepository;
import eu.napcode.recipes.repository.RecipesRepositoryImpl;

@Module
public interface RepositoryModule {

    @Binds
    RecipesRepository recipesRepository(RecipesRepositoryImpl recipesRepository);
}
