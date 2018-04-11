package eu.napcode.recipes.ui.ingredients;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.napcode.recipes.R;
import eu.napcode.recipes.databinding.ItemIngredientBinding;
import eu.napcode.recipes.model.Ingredient;

class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    private List<Ingredient> ingredients = new ArrayList<>();

    public IngredientsAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemIngredientBinding itemIngredientBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_ingredient, parent, false);

        return new IngredientsViewHolder(itemIngredientBinding);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        Ingredient ingredient = this.ingredients.get(position);

        holder.itemIngredientBinding.ingredientNameTextView.setText(ingredient.getIngredient());
        holder.itemIngredientBinding.measureTextView.setText(String.format("%s %s", ingredient.getQuantity(), ingredient.getMeasure()));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {
        public final ItemIngredientBinding itemIngredientBinding;

        public IngredientsViewHolder(ItemIngredientBinding itemIngredientBinding) {
            super(itemIngredientBinding.getRoot());

            this.itemIngredientBinding = itemIngredientBinding;
        }
    }
}
