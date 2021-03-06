package eu.napcode.recipes.dependency.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.napcode.recipes.RecipeDataBase;
import eu.napcode.recipes.dao.ingredients.IngredientDao;
import eu.napcode.recipes.dao.recipe.RecipeDao;
import eu.napcode.recipes.dao.step.StepDao;

@Module
public class StorageModule {

    @Singleton
    @Provides
    RecipeDataBase bookDataBase(Context context) {
        return Room
                .databaseBuilder(context, RecipeDataBase.class, RecipeDataBase.RECIPES_DATA_BASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    RecipeDao bookDao(Context context) {
        return bookDataBase(context).recipeDao();
    }

    @Singleton
    @Provides
    StepDao stepDao(Context context) {
        return bookDataBase(context).stepDao();
    }

    @Singleton
    @Provides
    IngredientDao ingredientDao(Context context) {
        return bookDataBase(context).ingredientDao();
    }
}
