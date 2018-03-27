package eu.napcode.recipes.dao;

import eu.napcode.recipes.model.Recipe;

public class RecipeMapper {

    public static RecipeEntity getEntityFromRecipe(Recipe recipe) {

        if (recipe == null) {
            return null;
        }

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(recipe.getId());
        recipeEntity.setName(recipe.getName());
        recipeEntity.setImageUrl(recipe.getImage());
        recipeEntity.setServings(recipe.getServings());

        return recipeEntity;
    }
}
