package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "Review")
public class ReviewRecord {

    private String reviewId;
    private String reviewedById;
    private String recipeId;
    private Double rating;
    private String comment;

    @DynamoDBHashKey(attributeName = "recipeId")
    public String getRecipeId() {
        return recipeId;
    }
    @DynamoDBRangeKey(attributeName = "reviewedById")
    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
    @DynamoDBAttribute(attributeName = "reviewedBy")
    public String getReviewedById() {
        return reviewedById;
    }

    public void setReviewedById(String reviewedById) {
        this.reviewedById = reviewedById;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }
    @DynamoDBAttribute(attributeName = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
    @DynamoDBAttribute(attributeName = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
