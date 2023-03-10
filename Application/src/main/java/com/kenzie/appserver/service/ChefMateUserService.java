package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.repositories.ChefMateUserRepository;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefMateUserService {

    private ChefMateUserRepository chefMateUserRepository;
    private LambdaServiceClient lambdaServiceClient;

    public ChefMateUserService(ChefMateUserRepository chefMateUserRepository, LambdaServiceClient lambdaServiceClient) {
        this.chefMateUserRepository = chefMateUserRepository;
        this.lambdaServiceClient = lambdaServiceClient;
    }

    /**
     * getUserById
     * @param userId
     * @return The User with the given userId
     */
    public ChefMateUserResponse getUserById(String userId) {

        return null;

    }

    /**
     * addNewUser
     *
     * This creates a new user
     * @param chefMateUserRequest
     * @return A ChefMateUserResponse describing the user
     */
    public ChefMateUserResponse addNewUser(CreateChefMateUserRequest chefMateUserRequest) {

        return null;
    }

    /**
     * updateUserPreferences - This updates the user profile for the given user id
     * @param userId - The id of the user to update
     * @param userPreferences   - The list of user dietary preferences
     */
    public ChefMateUserResponse updateUserPreferences(String userId, List<String> userPreferences) {

        return null;
    }

    /**
     * updateRecipesTried - This updates the user profile for the given user id
     * @param userId - The id of the user to update
     * @param recipesTried   - The set of recipes tried by the user
     */
    public ChefMateUserResponse updateRecipesTried(String userId, List<String> recipesTried) {

        return null;
    }

    /**
     * deleteUser - This deletes the user record for the given userId
     * @param userId
     */
    public void deleteUser(String userId) {

    }

        /* -----------------------------------------------------------------------------------------------------------
        Private Methods
       ----------------------------------------------------------------------------------------------------------- */


}
