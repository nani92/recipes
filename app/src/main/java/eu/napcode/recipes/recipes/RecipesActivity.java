package eu.napcode.recipes.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.R;
import eu.napcode.recipes.databinding.ActivityRecipesBinding;
import eu.napcode.recipes.recipedetails.RecipeDetailsActivity;
import eu.napcode.recipes.repository.Resource;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelFactory;
import eu.napcode.recipes.model.Recipe;

import static eu.napcode.recipes.recipedetails.RecipeDetailsActivity.RECIPE_ID_KEY;
import static eu.napcode.recipes.recipedetails.RecipeDetailsActivity.RECIPE_NAME;

public class RecipesActivity extends AppCompatActivity implements RecipesAdapter.RecipeClickListener {

    private static final int LANDSCAPE_COLUMN_COUNT = 2;
    private static final int TABLET_LANDSCAPE_COLUMN_COUNT = 3;
    @Inject
    ViewModelFactory viewModelFactory;

    private ActivityRecipesBinding binding;
    private RecipesViewModel mainViewModel;
    private RecipesAdapter recipesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_recipes);

        AndroidInjection.inject(this);
        setupRecyclerView();

        mainViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(RecipesViewModel.class);

        mainViewModel.getRecipes().observe(this, this::processResponse);
    }

    private void processResponse(Resource<List<Recipe>> recipeListResource) {

        if (recipeListResource.status == Resource.Status.LOADING) {
            //TODO show pb
        } else {
            //TODO hide pb
        }

        if (recipeListResource.status == Resource.Status.ERROR) {
            displayMessage(recipeListResource.message);

            return;
        }

        if (recipeListResource.status == Resource.Status.SUCCESS) {
            displayRecipes(recipeListResource.data);
        }
    }

    private void displayMessage(String message) {
        Snackbar.make(this.binding.recipesRecyclerView, message, Snackbar.LENGTH_LONG);
    }

    private void displayRecipes(List<Recipe> data) {
        this.recipesAdapter.setRecipes(data);
    }

    private void setupRecyclerView() {
        setupLayoutManager();
        this.recipesAdapter = new RecipesAdapter(this, this);
        this.binding.recipesRecyclerView.setAdapter(recipesAdapter);
    }

    private void setupLayoutManager() {
        
        if (getResources().getBoolean(R.bool.isTablet)) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                this.binding.recipesRecyclerView.setLayoutManager(new GridLayoutManager(this, LANDSCAPE_COLUMN_COUNT));
            } else {
                this.binding.recipesRecyclerView.setLayoutManager(new GridLayoutManager(this, TABLET_LANDSCAPE_COLUMN_COUNT));
            }

            return;
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            this.binding.recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            this.binding.recipesRecyclerView.setLayoutManager(new GridLayoutManager(this, LANDSCAPE_COLUMN_COUNT));
        }
    }

    @Override
    public void onRecipeClicked(Recipe recipe) {
        Intent intent = new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra(RECIPE_ID_KEY, recipe.getId());
        intent.putExtra(RECIPE_NAME, recipe.getName());

        startActivity(intent);
    }
}
