package eu.napcode.recipes.dao.step;

import android.arch.persistence.room.Entity;

import static eu.napcode.recipes.dao.step.StepEntity.TABLE_NAME;

@Entity(tableName = TABLE_NAME,
        primaryKeys = {"id", "recipeId"})
public class StepEntity {

    static final String TABLE_NAME = "steps";

    private int id;
    private int recipeId;
    private String description;
    private String shortDescription;
    private String videoUrl;
    private String thumbnailUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
