package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeResponse implements Serializable {

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

    public RecipeResponse(String recipeId, String name, String image, String instructions, int readyInMinutes, String sourceUrl, int servings) {
        this.recipeId = recipeId;
        this.name = name;
        this.image = image;
        this.instructions = instructions;
        this.readyInMinutes = readyInMinutes;
        this.sourceUrl = sourceUrl;
        this.servings = servings;
    }

    public RecipeResponse() {
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
