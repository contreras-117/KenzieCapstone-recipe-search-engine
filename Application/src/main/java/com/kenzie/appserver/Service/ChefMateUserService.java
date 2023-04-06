package com.kenzie.appserver.Service;

import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.repositories.ChefMateUserRepository;
import com.kenzie.appserver.repositories.model.ChefMateUserRecord;
import com.kenzie.capstone.service.client.RecipeServiceLambdaJavaClient.RecipeLambdaServiceClient;
import com.kenzie.capstone.service.client.ReviewServiceLambdaJavaClient.ReviewLambdaServiceClient;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.Recipe;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.Review;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChefMateUserService {

    private ChefMateUserRepository chefMateUserRepository;
    private ReviewLambdaServiceClient reviewLambdaServiceClient;
    private RecipeLambdaServiceClient recipeLambdaServiceClient;

    public ChefMateUserService(ChefMateUserRepository chefMateUserRepository,
                               ReviewLambdaServiceClient reviewLambdaServiceClient,
                               RecipeLambdaServiceClient recipeLambdaServiceClient) {
        this.chefMateUserRepository = chefMateUserRepository;
        this.reviewLambdaServiceClient = reviewLambdaServiceClient;
        this.recipeLambdaServiceClient = recipeLambdaServiceClient;
    }

    /**
     * getUserById
     * @param userId
     * @return The user with the given userId
     */
    public ChefMateUserResponse getUserById(String userId) {

        return Optional.ofNullable(chefMateUserRepository.findById(userId))
                .orElse(Optional.empty())
                .map(this::toChefMateUserResponse)
                .orElse(null);

    }

    /**
     * addNewUser
     *
     * This creates a new user.
     * @param createChefMateUserRequest
     * @return A CustomerResponse describing the customer
     */
    public ChefMateUserResponse addNewUser(CreateChefMateUserRequest createChefMateUserRequest) {

        Optional<List<String>> userPreferences = createChefMateUserRequest.getUserPreferences();
        Optional<Set<String>> recipesTried = createChefMateUserRequest.getRecipesTried();
        Optional<Set<String>> ingredients = createChefMateUserRequest.getIngredients();

        ChefMateUserRecord newRecord = new ChefMateUserRecord();
        newRecord.setUserId(createChefMateUserRequest.getUserId());
        userPreferences.ifPresent(newRecord::setUserPreferences);
        recipesTried.ifPresent(newRecord::setRecipesTried);
        ingredients.ifPresent(newRecord::setIngredients);

        chefMateUserRepository.save(newRecord);
        return toChefMateUserResponse(newRecord);
    }

    /**
     * updateUserPreferences - This updates the list of userPreferences for the given user id
     * @param userId - The Id of the user to update
     * @param userPreferences - The new preferences for the user
     */
    public ChefMateUserResponse updateUserPreferences(String userId, List<String> userPreferences) {

        Optional<ChefMateUserRecord> user = chefMateUserRepository.findById(userId);
        ChefMateUserRecord userRecord = user
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found"));

        userRecord.setUserPreferences(userPreferences);
        chefMateUserRepository.save(userRecord);

        return toChefMateUserResponse(userRecord);
    }

    /**
     * updateUserPreferences - This updates the set of recipesTried for the given user id
     * @param userId - The Id of the user to update
     * @param recipesTried - The new recipesTried for the user
     */
    public ChefMateUserResponse updateRecipesTried(String userId, Set<String> recipesTried) {

        Optional<ChefMateUserRecord> user = chefMateUserRepository.findById(userId);
        ChefMateUserRecord userRecord = user
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found"));

        userRecord.setRecipesTried(recipesTried);
        chefMateUserRepository.save(userRecord);

        return toChefMateUserResponse(userRecord);
    }

    /**
     * deleteUser - This deletes the user record for the given user id
     * @param userId
     */
    public void deleteUser(String userId) {
        chefMateUserRepository.deleteById(userId);
    }

    public ReviewResponse addReview(ReviewCreateRequest request) {
        Optional<ChefMateUserRecord> userExists = chefMateUserRepository.findById(request.getReviewerId());
        if (userExists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found");
        }

        ChefMateUserRecord userRecord = userExists.get();

        if (!userRecord.getRecipesTried().contains(request.getRecipeId())) {
            throw new IllegalArgumentException("Cannot review recipe you havent tried");
        }

        return reviewLambdaServiceClient.addReview(request);
    }

    public List<ReviewResponse> getRecipeReviews(String recipeId) {
        if (recipeId == null || recipeId.length() == 0) {
            throw new IllegalArgumentException("Recipe Id cannot be null");
        }

        return Optional.ofNullable(reviewLambdaServiceClient.getRecipeReviews(recipeId))
                .orElse(Collections.emptyList())
                .stream()
                .map(this::reviewToResponseConverter)
                .collect(Collectors.toList());
    }

    public List<RecipeResponse> getAllRecipes(String query){
        return recipeLambdaServiceClient.getAllRecipes(query);
    }

    public List<Recipe> getRandomRecipe(){
        return recipeLambdaServiceClient.getRandomRecipe();
    }

    public List<RecipeResponse> searchByNutrients(String query) {
        return recipeLambdaServiceClient.getSearchByNutrients(query);
    }


    /* -----------------------------------------------------------------------------------------------------------
    Private Methods
   ----------------------------------------------------------------------------------------------------------- */
    private ChefMateUserResponse toChefMateUserResponse(ChefMateUserRecord record) {

        if (record == null) {
            return null;
        }

        ChefMateUserResponse response = new ChefMateUserResponse();
        response.setUserId(record.getUserId());
        Optional.ofNullable(record.getUserPreferences())
                .ifPresent(userPreferences -> {
                    response.setUserPreferences(record.getUserPreferences());
                });
        Optional.ofNullable(record.getRecipesTried())
                .ifPresent(recipesTried -> {
                    response.setRecipesTried(record.getRecipesTried());
                });
        Optional.ofNullable(record.getIngredients())
                .ifPresent(ingredients -> {
                    response.setIngredients(record.getIngredients());
                });

        return response;
    }

    private ReviewResponse reviewToResponseConverter(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setReviewerId(review.getReviewerId());
        response.setComment(review.getComment());
        response.setRecipeId(review.getRecipeId());
        response.setRating(review.getRating());
        return response;
    }


}
