package com.kenzie.capstone.service.model.ReviewServiceLambdaModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;


public class ReviewCreateRequest {
    @NotNull
    @JsonProperty("recipeId")
    private String recipeId;
    @NotNull
    @JsonProperty("reviewerId")
    private String reviewerId;
    @NotNull
    @JsonProperty("rating")
    private Double rating;
    @NotNull
    @JsonProperty("comment")
    private String comment;

    public ReviewCreateRequest(String recipeId, String reviewerId, Double rating, String comment) {
        this.comment = comment;
        this.rating = rating;
        this.reviewerId = reviewerId;
        this.recipeId = recipeId;
    }

    public ReviewCreateRequest() {

    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
