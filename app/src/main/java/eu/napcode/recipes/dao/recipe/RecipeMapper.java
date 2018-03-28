package eu.napcode.recipes.dao.recipe;

import eu.napcode.recipes.model.Recipe;

public class RecipeMapper {

    public static RecipeEntity toEntity(Recipe recipe) {

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

    public static Recipe toRecipe(RecipeEntity recipeEntity) {

        if (recipeEntity == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setId(recipeEntity.getId());
        recipe.setName(recipeEntity.getName());
        recipe.setImage(recipeEntity.getImageUrl());
        recipe.setServings(recipeEntity.getServings());

        return recipe;
    }
}
