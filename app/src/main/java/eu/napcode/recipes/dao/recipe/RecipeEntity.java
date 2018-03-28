package eu.napcode.recipes.dao.recipe;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static eu.napcode.recipes.dao.recipe.RecipeEntity.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class RecipeEntity {

    static final String TABLE_NAME = "recipes";

    @PrimaryKey
    private int id;
    private String name;
    private int servings;
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
