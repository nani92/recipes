package eu.napcode.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.api.RecipeService;
import eu.napcode.recipes.databinding.ActivityMainBinding;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    RecipeService recipeService;

    @Inject
    ViewModelFactory viewModelFactory;

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        AndroidInjection.inject(this);
        isTwoPane = this.binding.listLayout.recipeDetailContainer != null;

        mainViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainViewModel.class);
    }
}
