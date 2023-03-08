package com.kenzie.capstone.service.model;

import java.util.Objects;

public class Review {
    private String recipeId;
    private String reviewerId;
    private Double rating;
    private String comment;


    public Review(String recipeId, String reviewerId, Double rating, String comment) {
        this.recipeId = recipeId;
        this.reviewerId = reviewerId;
        this.rating = rating;
        this.comment = comment;
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

    public Review() {}


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Review that = (Review) o;
        return Objects.equals(recipeId, that.recipeId) && Objects.equals(reviewerId, that.reviewerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, reviewerId);
    }
}
