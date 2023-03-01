package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ReviewCreateRequest {
    @NotEmpty
    @JsonProperty("reviewedById")
    private String reviewedById;
    @NotEmpty
    @JsonProperty("recipeId")
    private String recipeId;
    @NotEmpty
    @Min(0)
    @Max(5)
    @JsonProperty("reviewedById")
    private Double rating;
    @JsonProperty("comment")
    private String comment;

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
