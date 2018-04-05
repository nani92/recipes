package eu.napcode.recipes.ui.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.animation.AnimationUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.R;
import eu.napcode.recipes.ui.cupcake.CupcakeFragment;
import eu.napcode.recipes.databinding.ActivityRecipeDetailsBinding;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelFactory;
import eu.napcode.recipes.model.Step;
import eu.napcode.recipes.repository.Resource;
import eu.napcode.recipes.ui.step.StepActivity;
import eu.napcode.recipes.ui.step.StepFragment;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsAdapter.RecipeDetailClickListener {

    public static final String RECIPE_ID_KEY = "recipe id";
    public static final String RECIPE_NAME = "recipe name";

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

        getSupportActionBar().setTitle(getIntent().getStringExtra(RECIPE_NAME));
        displayEmptyViewForTwoPane();
    }

    private void setupRecyclerView() {
        this.binding.recipeDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recipesDetailsAdapter = new RecipeDetailsAdapter(this,this);
        this.binding.recipeDetailsRecyclerView.setAdapter(recipesDetailsAdapter);
        this.binding.recipeDetailsRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_rv));
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
                this.binding.recipeDetailsRecyclerView.scheduleLayoutAnimation();

                break;
            case ERROR:
                //TODO go back?
                break;
        }
    }

    private void displayEmptyViewForTwoPane() {
        boolean isTwoPane = this.binding.detailsContainer != null;

        if (isTwoPane) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailsContainer, new CupcakeFragment())
                    .commit();
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
        StepFragment fragment = StepFragment.newInstance(step.getId(), getIntent().getIntExtra(RECIPE_ID_KEY, 0));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detailsContainer, fragment)
                .commit();
    }

    private void displayStepActivity(Step step) {
        Intent intent = new Intent(this, StepActivity.class);
        intent.putExtra(StepActivity.STEP_ID_KEY, step.getId());
        intent.putExtra(StepActivity.RECIPE_ID_KEY, getIntent().getIntExtra(RECIPE_ID_KEY, 0));

        startActivity(intent);
    }

    @Override
    public void onIngredientsClicked() {

    }
}
