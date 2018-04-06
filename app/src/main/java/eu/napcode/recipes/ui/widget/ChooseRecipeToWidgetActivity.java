package eu.napcode.recipes.ui.widget;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import eu.napcode.recipes.R;
import eu.napcode.recipes.databinding.ActivityChooseRecipeToWidgetBinding;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelFactory;
import eu.napcode.recipes.model.Recipe;
import eu.napcode.recipes.repository.Resource;
import eu.napcode.recipes.ui.recipes.RecipesViewModel;

import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;
import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;

public class ChooseRecipeToWidgetActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    private ActivityChooseRecipeToWidgetBinding binding;
    private int appWidgetId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_recipe_to_widget);

        AndroidInjection.inject(this);

        ViewModelProviders.of(this, viewModelFactory)
                .get(RecipesViewModel.class)
                .getRecipes()
                .observe(this, this::processResponse);
        initializeAppWidget();

        this.binding.addWidgetButton.setOnClickListener(view -> setActivityResult());
    }

    private void processResponse(Resource<List<Recipe>> listResource) {

        if (listResource.status == Resource.Status.LOADING) {
            //TODO show pb
        } else {
            //TODO hide pb
        }

        if (listResource.status == Resource.Status.ERROR) {
            displayMessage(listResource.message);

            return;
        }

        if (listResource.status == Resource.Status.SUCCESS) {
            displayRecipes(listResource.data);
        }
    }

    private void displayRecipes(List<Recipe> recipes) {

        for (Recipe recipe: recipes) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(recipe.getName());
            this.binding.radioGroup.addView(radioButton);
        }
    }

    private void displayMessage(String message) {
        Snackbar.make(this.binding.container, message, Snackbar.LENGTH_LONG);
    }

    private void initializeAppWidget(){
        this.appWidgetId = INVALID_APPWIDGET_ID;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            this.appWidgetId = extras.getInt(EXTRA_APPWIDGET_ID, INVALID_APPWIDGET_ID);
        }

        if (this.appWidgetId == INVALID_APPWIDGET_ID) {
            finish();
        }
    }

    private void setActivityResult() {
        Intent resultValue = new Intent();
        resultValue.putExtra(EXTRA_APPWIDGET_ID, this.appWidgetId);
        setResult(RESULT_OK, resultValue);

        finish();
    }
}
