package eu.napcode.recipes.dependency.modules;

import dagger.Binds;
import dagger.Module;
import eu.napcode.recipes.repository.recipes.RecipesRepository;
import eu.napcode.recipes.repository.recipes.RecipesRepositoryImpl;

@Module
public interface RepositoryModule {

    @Binds
    RecipesRepository recipesRepository(RecipesRepositoryImpl recipesRepository);
}
