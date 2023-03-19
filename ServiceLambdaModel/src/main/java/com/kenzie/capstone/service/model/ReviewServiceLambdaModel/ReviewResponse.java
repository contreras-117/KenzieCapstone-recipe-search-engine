package com.kenzie.capstone.service.model.ReviewServiceLambdaModel;

public class ReviewResponse {

    private String recipeId;
    private String reviewerId;
    private Double rating;
    private String comment;

    public ReviewResponse(String recipeId, String reviewerId, Double rating, String comment) {
        this.recipeId = recipeId;
        this.reviewerId = reviewerId;
        this.rating = rating;
        this.comment = comment;
    }

    public ReviewResponse() {
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
