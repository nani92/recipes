package eu.napcode.recipes.recipedetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.R;
import eu.napcode.recipes.model.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        AndroidInjection.inject(this);

        RecipeDetailsFragment fragment = RecipeDetailsFragment.newInstance((Recipe) getIntent().getParcelableExtra(RecipeDetailsFragment.RECIPE_KEY));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.recipeDetailsContainer, fragment)
                .commit();
    }
}
