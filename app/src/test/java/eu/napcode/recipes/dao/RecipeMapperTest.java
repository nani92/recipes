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
        RecipeEntity recipeEntity = RecipeMapper.toEntity(recipe);

        Assert.assertEquals(recipe.getId(), recipeEntity.getId());
    }

    @Test
    public void testRecipeToEntityNameMapping() {
        Recipe recipe = getRecipe();
        RecipeEntity recipeEntity = RecipeMapper.toEntity(recipe);

        Assert.assertEquals(recipe.getName(), recipeEntity.getName());
    }

    @Test
    public void testRecipeToEntityImageMapping() {
        Recipe recipe = getRecipe();
        RecipeEntity recipeEntity = RecipeMapper.toEntity(recipe);

        Assert.assertEquals(recipe.getImage(), recipeEntity.getImageUrl());
    }

    @Test
    public void testRecipeToEntityServingsMapping() {
        Recipe recipe = getRecipe();
        RecipeEntity recipeEntity = RecipeMapper.toEntity(recipe);

        Assert.assertEquals(recipe.getServings(), recipeEntity.getServings());
    }

    @Test
    public void testRecipeToEntityNullMap() {
        RecipeEntity recipeEntity = RecipeMapper.toEntity(null);

        Assert.assertEquals(null, recipeEntity);
    }

    @Test
    public void testEntityToRecipeIdMapping() {
        RecipeEntity recipeEntity = getRecipeEntity();
        Recipe recipe = RecipeMapper.toRecipe(recipeEntity);

        Assert.assertEquals(recipeEntity.getId(), recipe.getId());
    }

    @Test
    public void testEntityToRecipeNameMapping() {
        RecipeEntity recipeEntity = getRecipeEntity();
        Recipe recipe = RecipeMapper.toRecipe(recipeEntity);

        Assert.assertEquals(recipeEntity.getName(), recipe.getName());
    }

    @Test
    public void testEntityToRecipeImageMapping() {
        RecipeEntity recipeEntity = getRecipeEntity();
        Recipe recipe = RecipeMapper.toRecipe(recipeEntity);

        Assert.assertEquals(recipeEntity.getImageUrl(), recipe.getImage());
    }

    @Test
    public void testEntityToRecipeServingsMapping() {
        RecipeEntity recipeEntity = getRecipeEntity();
        Recipe recipe = RecipeMapper.toRecipe(recipeEntity);

        Assert.assertEquals(recipeEntity.getServings(), recipe.getServings());
    }

    @Test
    public void testEntityToRecipeNullMap() {
        Recipe recipe = RecipeMapper.toRecipe(null);

        Assert.assertEquals(null, recipe);
    }

    public Recipe getRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setImage("image");
        recipe.setName("name");
        recipe.setServings(3);

        return recipe;
    }

    public RecipeEntity getRecipeEntity() {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(1);
        recipeEntity.setImageUrl("image");
        recipeEntity.setName("name");
        recipeEntity.setServings(3);

        return recipeEntity;
    }
}