package eu.napcode.recipes.ui.recipes;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import eu.napcode.recipes.R;
import eu.napcode.recipes.databinding.ItemRecipeBinding;
import eu.napcode.recipes.model.Recipe;

class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>{

    private Context context;
    private List<Recipe> recipes = new ArrayList<>();
    private RecipeClickListener recipeClickListener;

    public RecipesAdapter(Context context, RecipeClickListener recipeClickListener) {
        this.recipeClickListener = recipeClickListener;
        this.context = context;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;

        notifyDataSetChanged();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRecipeBinding itemRecipeBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_recipe, parent, false);

        return new RecipeViewHolder(itemRecipeBinding);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        holder.itemRecipeBinding.recipeNameTextView.setText(recipe.getName());
        holder.itemRecipeBinding.recipeConstraintLayout.setOnClickListener(view -> recipeClickListener.onRecipeClicked(recipe));
        String servings = String.format(context.getString(R.string.servings_format), recipe.getServings());
        holder.itemRecipeBinding.servingsTextView.setText(servings);

        if (TextUtils.isEmpty(recipe.getImage())) {
            return;
        }

        Picasso.get()
                .load(recipe.getImage())
                .into(holder.itemRecipeBinding.recipeImageView);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public final ItemRecipeBinding itemRecipeBinding;

        public RecipeViewHolder(ItemRecipeBinding itemRecipeBinding) {
            super(itemRecipeBinding.getRoot());

            this.itemRecipeBinding = itemRecipeBinding;
        }
    }

    public interface RecipeClickListener {
        void onRecipeClicked(Recipe recipe);
    }
}
