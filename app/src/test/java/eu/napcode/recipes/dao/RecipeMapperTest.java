package eu.napcode.recipes.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import eu.napcode.recipes.model.Recipe;

@RunWith(MockitoJUnitRunner.class)
public class RecipeMapperTest {

    @Test
    public void testRecipeToEntityIdMapping() {
        Recipe recipe = getRecipe();
        RecipeEntity recipeEntity = RecipeMapper.getEntityFromRecipe(recipe);

        Assert.assertEquals(recipe.getId(), recipeEntity.getId());
    }

    @Test
    public void testRecipeToEntityNameMapping() {
        Recipe recipe = getRecipe();
        RecipeEntity recipeEntity = RecipeMapper.getEntityFromRecipe(recipe);

        Assert.assertEquals(recipe.getName(), recipeEntity.getName());
    }

    @Test
    public void testRecipeToEntityImageMapping() {
        Recipe recipe = getRecipe();
        RecipeEntity recipeEntity = RecipeMapper.getEntityFromRecipe(recipe);

        Assert.assertEquals(recipe.getImage(), recipeEntity.getImageUrl());
    }

    @Test
    public void testRecipeToEntityServingsMapping() {
        Recipe recipe = getRecipe();
        RecipeEntity recipeEntity = RecipeMapper.getEntityFromRecipe(recipe);

        Assert.assertEquals(recipe.getServings(), recipeEntity.getServings());
    }

    @Test
    public void testRecipeToEntityNullMap() {
        RecipeEntity recipeEntity = RecipeMapper.getEntityFromRecipe(null);

        Assert.assertEquals(null, recipeEntity);
    }

    public Recipe getRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setImage("image");
        recipe.setName("name");
        recipe.setServings(3);

        return recipe;
    }

}