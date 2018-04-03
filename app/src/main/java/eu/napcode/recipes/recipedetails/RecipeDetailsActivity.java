package eu.napcode.recipes.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.R;
import eu.napcode.recipes.databinding.ActivityRecipeDetailsBinding;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelFactory;
import eu.napcode.recipes.model.Step;
import eu.napcode.recipes.repository.Resource;
import eu.napcode.recipes.step.StepActivity;
import eu.napcode.recipes.step.StepFragment;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsAdapter.RecipeDetailClickListener {

    public static final String RECIPE_ID_KEY = "recipe id";

    private ActivityRecipeDetailsBinding binding;
    private RecipeDetailsAdapter recipesDetailsAdapter;

    @Inject
    ViewModelFactory viewModelFactory;
    private RecipeDetailsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_details);

        AndroidInjection.inject(this);

        setupRecyclerView();
        setupViewModel();
        viewModel.getSteps().observe(this, this::processResponse);
    }

    private void setupRecyclerView() {
        this.binding.recipeDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recipesDetailsAdapter = new RecipeDetailsAdapter(this);
        this.binding.recipeDetailsRecyclerView.setAdapter(recipesDetailsAdapter);
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(RecipeDetailsViewModel.class);
        viewModel.setRecipeId(getIntent().getIntExtra(RECIPE_ID_KEY, 0));
    }

    private void processResponse(Resource<List<Step>> stepResource) {

        switch (stepResource.status) {
            case LOADING:
                //TODO
                break;
            case SUCCESS:
                this.recipesDetailsAdapter.setSteps(stepResource.data);
                break;
            case ERROR:
                //TODO go back?
                break;
        }
    }

    @Override
    public void onStepClicked(Step step) {
        boolean isTwoPane = this.binding.detailsContainer != null;

        if (isTwoPane) {
            displayStepFragment(step);
        } else {
            displayStepActivity(step);
        }
    }

    private void displayStepFragment(Step step) {
        StepFragment fragment = StepFragment.newInstance(step);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detailsContainer, fragment)
                .commit();
    }

    private void displayStepActivity(Step step) {
        Intent intent = new Intent(this, StepActivity.class);
        intent.putExtra(StepActivity.STEP_KEY, step);

        startActivity(intent);
    }

    @Override
    public void onIngredientsClicked() {

    }
}
