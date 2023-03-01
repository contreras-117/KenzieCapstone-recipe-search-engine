package com.kenzie.appserver.service.model;

public class Review {
    private String reviewId;
    private String reviewedById;
    private String recipeId;
    private Double rating;
    private String comment;

    public Review(String reviewId, String reviewedById, String recipeId, Double rating, String comment) {
        this.reviewId = reviewId;
        this.reviewedById = reviewedById;
        this.recipeId = recipeId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewedById() {
        return reviewedById;
    }

    public void setReviewedById(String reviewedById) {
        this.reviewedById = reviewedById;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
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
