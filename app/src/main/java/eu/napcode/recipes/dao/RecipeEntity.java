package eu.napcode.recipes.dao;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import eu.napcode.recipes.model.Step;

import static eu.napcode.recipes.dao.RecipeEntity.TABLE_NAME;


@Entity(tableName = TABLE_NAME)
public class RecipeEntity {

    static final String TABLE_NAME = "recipes";

    @PrimaryKey
    private int id;
    private String name;
    private int servings;
    private String imageUrl;
//    private List<Step> steps;

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

//    public List<Step> getSteps() {
//        return steps;
//    }
//
//    public void setSteps(List<Step> steps) {
//        this.steps = steps;
//    }
}
