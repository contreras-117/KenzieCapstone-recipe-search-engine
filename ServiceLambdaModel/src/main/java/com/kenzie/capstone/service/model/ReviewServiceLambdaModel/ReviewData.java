package com.kenzie.capstone.service.model.ReviewServiceLambdaModel;

public class ReviewData {
    private String recipeId;
    private String reviewerId;
    private Double rating;
    private String comment;

    public ReviewData(String recipeId, String reviewerId, Double rating, String comment) {
        this.comment = comment;
        this.rating = rating;
        this.reviewerId = reviewerId;
        this.recipeId = recipeId;
    }
    public ReviewData() {
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
