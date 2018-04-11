package eu.napcode.recipes.dao.ingredients;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static eu.napcode.recipes.dao.ingredients.IngredientEntity.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class IngredientEntity {

    static final String TABLE_NAME = "ingredients";

    @PrimaryKey(autoGenerate = true)
    long id;

    String name;
    float quantity;
    String measure;
    int recipeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
