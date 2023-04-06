package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Recipe implements Serializable {
    @SerializedName("id")
    private String recipeId;
    @SerializedName("title")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("instructions")
    private String instructions;
    @SerializedName("readyInMinutes")
    private int readyInMinutes;
    @SerializedName("sourceUrl")
    private String sourceUrl;
    @SerializedName("servings")
    private int servings;


    public Recipe(String recipeId, String name, String image, String instructions, int readyInMinutes, String sourceUrl, int servings) {
        this.recipeId = recipeId;
        this.name = name;
        this.image = image;
        this.instructions = instructions;
        this.readyInMinutes = readyInMinutes;
        this.sourceUrl = sourceUrl;
        this.servings = servings;
    }

    public Recipe() {
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public int getServings() {
        return servings;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return readyInMinutes == recipe.readyInMinutes && servings == recipe.servings && recipeId.equals(recipe.recipeId) && name.equals(recipe.name) && image.equals(recipe.image) && instructions.equals(recipe.instructions) && sourceUrl.equals(recipe.sourceUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, name, image, instructions, readyInMinutes, sourceUrl, servings);
    }
}
