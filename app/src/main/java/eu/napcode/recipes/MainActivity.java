package eu.napcode.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

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
    private RecipesAdapter recipesAdapter;

    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        AndroidInjection.inject(this);
        isTwoPane = this.binding.listLayout.recipeDetailContainer != null;
        setupRecyclerView();

        mainViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainViewModel.class);
    }

    private void setupRecyclerView() {
        this.binding.listLayout.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recipesAdapter = new RecipesAdapter();
        this.binding.listLayout.recyclerView.setAdapter(recipesAdapter);
    }
}
