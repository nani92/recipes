package eu.napcode.recipes.dao.ingredients;

import java.util.ArrayList;
import java.util.List;

import eu.napcode.recipes.model.Ingredient;

public class IngredientMapper {

    public static List<Ingredient> toIngredients(List<IngredientEntity> ingredientEntities) {

        if (ingredientEntities == null || ingredientEntities.isEmpty()) {
            return new ArrayList<>();
        }

        List<Ingredient> ingredients = new ArrayList<>();

        for(IngredientEntity ingredientEntity : ingredientEntities) {
            ingredients.add(IngredientMapper.toIngredient(ingredientEntity));
        }

        return ingredients;
    }

    public static Ingredient toIngredient(IngredientEntity ingredientEntity) {

        if (ingredientEntity == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient(ingredientEntity.getName());
        ingredient.setMeasure(ingredientEntity.getMeasure());
        ingredient.setQuantity(ingredientEntity.getQuantity());

        return ingredient;
    }

    public static IngredientEntity toIngredientEntity(Ingredient ingredient, int recipeId) {

        if (ingredient == null) {
            return null;
        }

        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setRecipeId(recipeId);
        ingredientEntity.setName(ingredient.getIngredient());
        ingredientEntity.setQuantity(ingredient.getQuantity());
        ingredientEntity.setMeasure(ingredient.getMeasure());

        return ingredientEntity;
    }
}
