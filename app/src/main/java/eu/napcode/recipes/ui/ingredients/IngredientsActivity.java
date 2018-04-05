package eu.napcode.recipes.ui.ingredients;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.R;

public class IngredientsActivity extends AppCompatActivity {

    public static String RECIPE_ID_KEY = "recipe id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        IngredientsFragment fragment = IngredientsFragment.newInstance(
                getIntent().getIntExtra(RECIPE_ID_KEY, 0));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detailsContainer, fragment)
                .commit();
    }
}
