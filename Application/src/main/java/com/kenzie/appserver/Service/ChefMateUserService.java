package com.kenzie.appserver.Service;

import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.repositories.ChefMateUserRepository;
import com.kenzie.appserver.repositories.model.ChefMateUserRecord;
import com.kenzie.capstone.service.client.ReviewServiceLambdaJavaClient.ReviewLambdaServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ChefMateUserService {

    private ChefMateUserRepository chefMateUserRepository;
    private ReviewLambdaServiceClient reviewLambdaServiceClient;

    public ChefMateUserService(ChefMateUserRepository chefMateUserRepository,
                               ReviewLambdaServiceClient reviewLambdaServiceClient) {
        this.chefMateUserRepository = chefMateUserRepository;
        this.reviewLambdaServiceClient = reviewLambdaServiceClient;
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


}
