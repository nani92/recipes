package eu.napcode.recipes.api;


import java.util.List;

import eu.napcode.recipes.model.Recipe;
import io.reactivex.Observable;
import retrofit2.http.GET;

import static eu.napcode.recipes.api.ApiUtils.FILE_URL;

public interface RecipeService {

    @GET(FILE_URL)
    Observable<List<Recipe>> getRecipes();
}
