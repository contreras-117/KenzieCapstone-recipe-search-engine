package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "ChefMateUser")
public class ChefMateUserRecord {
    private String userId;
    private List<String> userPreferences;
    private Set<String> recipesTried;
    private Set<String> ingredients;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    @DynamoDBAttribute(attributeName = "userPreferences")
    public List<String> getUserPreferences() {
        return userPreferences;
    }

    @DynamoDBAttribute(attributeName = "recipesTried")
    public Set<String> getRecipesTried() {
        return recipesTried;
    }

    @DynamoDBAttribute(attributeName = "ingredients")
    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPreferences(List<String> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public void setRecipesTried(Set<String> recipesTried) {
        this.recipesTried = recipesTried;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChefMateUserRecord that = (ChefMateUserRecord) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

}
