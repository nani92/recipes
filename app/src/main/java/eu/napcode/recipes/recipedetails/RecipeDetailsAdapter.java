package eu.napcode.recipes.recipedetails;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.napcode.recipes.R;
import eu.napcode.recipes.databinding.ItemRecipeDetailBinding;
import eu.napcode.recipes.model.Step;

class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.RecipeDetailsViewHolder>{

    private List<Step> steps = new ArrayList<>();
    private RecipeDetailClickListener recipeDetailClickListener;

    //TODO add ingredients to display

    public RecipeDetailsAdapter(List<Step> steps, RecipeDetailClickListener recipeClickListener) {
        this.steps = steps;
        this.recipeDetailClickListener = recipeClickListener;
    }

    @Override
    public RecipeDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRecipeDetailBinding itemRecipeBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recipe_detail, parent, false);

        return new RecipeDetailsViewHolder(itemRecipeBinding);
    }

    @Override
    public void onBindViewHolder(RecipeDetailsViewHolder holder, int position) {
        Step step = steps.get(position);

        holder.itemRecipeDetailBinding.nameTextView.setText(step.getShortDescription());
        holder.itemRecipeDetailBinding.recipeDetailConstraintLayout.setOnClickListener(view -> recipeDetailClickListener.onStepClicked(step));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class RecipeDetailsViewHolder extends RecyclerView.ViewHolder {
        public final ItemRecipeDetailBinding itemRecipeDetailBinding;

        public RecipeDetailsViewHolder(ItemRecipeDetailBinding itemRecipeDetailBinding) {
            super(itemRecipeDetailBinding.getRoot());

            this.itemRecipeDetailBinding = itemRecipeDetailBinding;
        }
    }

    public interface RecipeDetailClickListener {
        void onStepClicked(Step step);

        void onIngredientsClicked();
    }
}
