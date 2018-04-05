package eu.napcode.recipes.ui.ingredients;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import eu.napcode.recipes.R;
import eu.napcode.recipes.databinding.FragmentIngredientsBinding;
import eu.napcode.recipes.dependency.modules.viewmodel.ViewModelFactory;
import eu.napcode.recipes.model.Ingredient;

public class IngredientsFragment extends Fragment {
    //TODO rv for ingredients, adapter, item, display

    @Inject
    ViewModelFactory viewModelFactory;

    public static final String RECIPE_ID_KEY = "recipe id";

    private IngredientsViewModel viewModel;
    private FragmentIngredientsBinding binding;

    public static IngredientsFragment newInstance( int recipeId) {
        Bundle bundle = new Bundle();
        bundle.putInt(RECIPE_ID_KEY, recipeId);

        IngredientsFragment fragment = new IngredientsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ingredients, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AndroidSupportInjection.inject(this);

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(IngredientsViewModel.class);

        viewModel.setRecipeInfo(
                getArguments().getInt(RECIPE_ID_KEY));

        viewModel.getIngredients().observe(this,
                ingredients -> displayIngredients(ingredients));
    }

    void displayIngredients(List<Ingredient> ingredients) {

        if (ingredients == null) {
            //TODO display something
        }

        this.binding.ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.ingredientsRecyclerView.setAdapter(new IngredientsAdapter(ingredients));
        this.binding.ingredientsRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_rv));
        this.binding.ingredientsRecyclerView.scheduleLayoutAnimation();
    }
}
