package eu.napcode.recipes.recipedetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.napcode.recipes.R;
import eu.napcode.recipes.model.Recipe;

public class RecipeDetailsFragment extends Fragment {

    public static final String RECIPE_KEY = "recipe";

    public static RecipeDetailsFragment newInstance(Recipe recipe) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_KEY, recipe);

        return newInstance(bundle);
    }

    public static RecipeDetailsFragment newInstance(Bundle recipeBundle) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        fragment.setArguments(recipeBundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
