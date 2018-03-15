package eu.napcode.recipes.api;


import java.util.List;

import eu.napcode.recipes.model.Recipe;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RecipeService {

    @GET()
    Observable<List<Recipe>> getMovies();
}
