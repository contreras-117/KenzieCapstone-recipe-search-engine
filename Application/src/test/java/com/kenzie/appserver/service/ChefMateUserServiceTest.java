package com.kenzie.appserver.service;

import com.kenzie.appserver.Service.ChefMateUserService;
import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.repositories.ChefMateUserRepository;
import com.kenzie.appserver.repositories.model.ChefMateUserRecord;
import com.kenzie.capstone.service.client.ReviewServiceLambdaJavaClient.ReviewLambdaServiceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class ChefMateUserServiceTest {

    private ChefMateUserRepository chefMateUserRepository;
    private ChefMateUserService chefMateUserService;

    @BeforeEach
    void setup() {
        chefMateUserRepository = mock(ChefMateUserRepository.class);
        ReviewLambdaServiceClient reviewLambdaServiceClient = mock(ReviewLambdaServiceClient.class);
        chefMateUserService = new ChefMateUserService(chefMateUserRepository, reviewLambdaServiceClient);
    }

    /** ------------------------------------------------------------------------
     *  chefMateUserService.addNewUser
     *  ------------------------------------------------------------------------ **/
    @Test
    void addNewUser() {
        // GIVEN
        String userId = randomUUID().toString();

        CreateChefMateUserRequest request = new CreateChefMateUserRequest();
        request.setUserId(userId);
        request.setUserPreferences(Optional.empty());
        request.setRecipesTried(Optional.empty());
        request.setIngredients(Optional.empty());

        ArgumentCaptor<ChefMateUserRecord> userRecordCaptor = ArgumentCaptor.forClass(ChefMateUserRecord.class);

        // WHEN
        ChefMateUserResponse returnedUser = chefMateUserService.addNewUser(request);

        // THEN
        Assertions.assertNotNull(returnedUser);

        verify(chefMateUserRepository).save(userRecordCaptor.capture());

        ChefMateUserRecord record = userRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The user record is returned");
        Assertions.assertNotNull(record.getUserId(), "The user id exists");
    }

    /** ------------------------------------------------------------------------
     *  chefMateUserService.findByUserId
     *  ------------------------------------------------------------------------ **/

    @Test
    void findByUserId_valid_user() {
        // GIVEN
        String userId = randomUUID().toString();

        ChefMateUserRecord record = new ChefMateUserRecord();
        record.setUserId(userId);

        when(chefMateUserRepository.findById(userId)).thenReturn(Optional.of(record));
        // WHEN
        ChefMateUserResponse user = chefMateUserService.getUserById(userId);

        // THEN
        Assertions.assertNotNull(user, "The user is returned");
        Assertions.assertEquals(record.getUserId(), user.getUserId(), "The user id matches");
    }

    @Test
    void findByUserId_invalid_user() {
        // GIVEN
        String userId = randomUUID().toString();

        // WHEN
        when(chefMateUserRepository.findById(userId)).thenReturn(Optional.empty());
        ChefMateUserResponse user = chefMateUserService.getUserById(userId);

        // THEN
        Assertions.assertNull(user, "The user is null when not found");
    }

    /** ------------------------------------------------------------------------
     *  chefMateUserService.updateUserPreferences
     *  ------------------------------------------------------------------------ **/

    @Test
    void updateUserPreferences_valid_user() {
        // GIVEN
        String userId = randomUUID().toString();

        ChefMateUserRecord oldUserRecord = new ChefMateUserRecord();
        oldUserRecord.setUserId(userId);
        List<String> newUserPreferences = Arrays.asList("Gluten Free", "Vegetarian", "Dairy Free");

        when(chefMateUserRepository.findById(userId)).thenReturn(Optional.of(oldUserRecord));

        ArgumentCaptor<ChefMateUserRecord> customerRecordCaptor = ArgumentCaptor.forClass(ChefMateUserRecord.class);

        // WHEN
        chefMateUserService.updateUserPreferences(userId, newUserPreferences);

        // THEN
        verify(chefMateUserRepository).save(customerRecordCaptor.capture());

        ChefMateUserRecord record = customerRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The user record is returned");
        Assertions.assertEquals(record.getUserId(), userId, "The user id matches");
    }

    @Test
    void updateUserPreferences_invalid_user() {
        // GIVEN
        String userId = randomUUID().toString();
        List<String> newUserPreferences = Arrays.asList("Gluten Free", "Vegetarian", "Dairy Free");


        when(chefMateUserRepository.findById(userId)).thenReturn(Optional.empty());

        // WHEN
        Assertions.assertThrows(ResponseStatusException.class, () -> chefMateUserService.updateUserPreferences(userId, newUserPreferences));

        // THEN
        try {
            verify(chefMateUserRepository, never()).save(Matchers.any());
        } catch(MockitoAssertionError error) {
            throw new MockitoAssertionError("There should not be a call to .save() if the user is not found in the database. - " + error);
        }
    }

    /** ------------------------------------------------------------------------
     *  chefMateUserService.updateUserPreferences
     *  ------------------------------------------------------------------------ **/

    @Test
    void updateRecipesTried_valid_user() {
        // GIVEN
        String userId = randomUUID().toString();

        ChefMateUserRecord oldUserRecord = new ChefMateUserRecord();
        oldUserRecord.setUserId(userId);
        Set<String> newRecipesTried = new HashSet<>();
        newRecipesTried.add("Italian Recipe");
        newRecipesTried.add("Chinese Recipe");

        when(chefMateUserRepository.findById(userId)).thenReturn(Optional.of(oldUserRecord));

        ArgumentCaptor<ChefMateUserRecord> userRecordCaptor = ArgumentCaptor.forClass(ChefMateUserRecord.class);

        // WHEN
        chefMateUserService.updateRecipesTried(userId, newRecipesTried);

        // THEN
        verify(chefMateUserRepository).save(userRecordCaptor.capture());

        ChefMateUserRecord record = userRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The user record is returned");
        Assertions.assertEquals(record.getUserId(), userId, "The user id matches");
    }

    @Test
    void updateRecipesTried_invalid_user() {
        // GIVEN
        String userId = randomUUID().toString();
        Set<String> newRecipesTried = new HashSet<>();
        newRecipesTried.add("Italian Recipe");
        newRecipesTried.add("Chinese Recipe");

        when(chefMateUserRepository.findById(userId)).thenReturn(Optional.empty());

        // WHEN
        Assertions.assertThrows(ResponseStatusException.class, () -> chefMateUserService.updateRecipesTried(userId, newRecipesTried));

        // THEN
        try {
            verify(chefMateUserRepository, never()).save(Matchers.any());
        } catch(MockitoAssertionError error) {
            throw new MockitoAssertionError("There should not be a call to .save() if the user is not found in the database. - " + error);
        }
    }


    /** ------------------------------------------------------------------------
     *  chefMateUserService.deleteUser
     *  ------------------------------------------------------------------------ **/
    @Test
    void deleteUser_valid_user() {
        // GIVEN
        String userId = randomUUID().toString();

        ChefMateUserRecord oldCustomerRecord = new ChefMateUserRecord();
        oldCustomerRecord.setUserId(userId);

        doNothing().when(chefMateUserRepository).deleteById(userId);

        // WHEN
        chefMateUserService.deleteUser(userId);

        // THEN
        verify(chefMateUserRepository).deleteById(userId);
    }

    @Test
    void deleteUser_invalid_user() {

        // GIVEN
        String userId = randomUUID().toString();

        when(chefMateUserRepository.findById(userId)).thenReturn(Optional.empty());

        // WHEN
        Assertions.assertThrows(ResponseStatusException.class, () -> chefMateUserService.deleteUser(userId));

        // THEN
        try {
            verify(chefMateUserRepository, never()).deleteById(Matchers.any());
        } catch(MockitoAssertionError error) {
            throw new MockitoAssertionError("There should not be a call to .deleteById() if the user is not found in the database. - " + error);
        }

    }


}
