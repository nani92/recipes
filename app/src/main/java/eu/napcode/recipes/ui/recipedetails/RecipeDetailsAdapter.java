package eu.napcode.recipes.ui.recipedetails;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
    private Context context;

    //TODO add ingredients to display

    public RecipeDetailsAdapter(Context context, RecipeDetailClickListener recipeClickListener) {
        this.recipeDetailClickListener = recipeClickListener;
        this.context = context;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
        notifyItemRangeInserted(0, steps.size());
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

        holder.itemRecipeDetailBinding.nameTextView.setText(getNameSpannable(step));
        holder.itemRecipeDetailBinding.detailsCardView.setOnClickListener(view -> recipeDetailClickListener.onStepClicked(step));
    }

    public SpannableString getNameSpannable(Step step) {
        String name = String.format("%d. %s", step.getId(), step.getShortDescription());
        int dotPosition = name.indexOf('.') + 1;
        int colorAccent = ContextCompat.getColor(context, R.color.colorAccent);
        int colorPrimary = ContextCompat.getColor(context, R.color.colorPrimary);

        SpannableString spannableString = new SpannableString(name);
        spannableString.setSpan(new ForegroundColorSpan(colorAccent),
                0,
                dotPosition,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        spannableString.setSpan(new ForegroundColorSpan(colorPrimary),
                dotPosition,
                name.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
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
