package eu.napcode.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.api.RecipeService;
import eu.napcode.recipes.databinding.ActivityMainBinding;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelFactory;
import eu.napcode.recipes.model.Recipe;

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

        mainViewModel.getRecipes().observe(this, this::processResponse);
    }

    private void processResponse(Resource<List<Recipe>> recipeListResource) {

        if (recipeListResource.status == Resource.Status.LOADING) {
            //TODO show pb
        } else {
            //TODO hide pb
        }

        if (recipeListResource.status == Resource.Status.ERROR) {
            Log.d("N err", recipeListResource.message);
        }

        if (recipeListResource.status == Resource.Status.SUCCESS) {
            displayRecipes(recipeListResource.data);
        }
    }

    private void displayRecipes(List<Recipe> data) {
        this.recipesAdapter.setRecipes(data);
    }

    private void setupRecyclerView() {
        this.binding.listLayout.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recipesAdapter = new RecipesAdapter();
        this.binding.listLayout.recyclerView.setAdapter(recipesAdapter);
    }
}
