package com.kenzie.capstone.service.model.ReviewServiceLambdaModel;

import com.fasterxml.jackson.annotation.JsonProperty;



public class ReviewCreateRequest {

    @JsonProperty("recipeId")
    private String recipeId;

    @JsonProperty("reviewerId")
    private String reviewerId;
    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("comment")
    private String comment;

    ReviewCreateRequest(String recipeId, String reviewerId, Double rating, String comment) {
        this.comment = comment;
        this.rating = rating;
        this.reviewerId = reviewerId;
        this.recipeId = recipeId;
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
