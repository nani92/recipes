package eu.napcode.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.api.RecipeService;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    RecipeService recipeService;

    @Inject
    ViewModelFactory viewModelFactory;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidInjection.inject(this);

        mainViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainViewModel.class);

    }
}
