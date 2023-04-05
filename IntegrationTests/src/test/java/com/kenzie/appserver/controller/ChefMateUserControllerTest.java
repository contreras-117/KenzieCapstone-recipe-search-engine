package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.Service.ChefMateUserService;
import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.controller.model.UpdateUserPreferencesRequest;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class ChefMateUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ChefMateUserService chefMateUserService;


    private static final MockNeat mockNeat = MockNeat.threadLocal();
    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public static void setup() {
        mapper.registerModule(new Jdk8Module());
    }

    /** ------------------------------------------------------------------------
     *  Add User
     *  ------------------------------------------------------------------------ **/

    @Test
    public void addUser_successful() throws Exception {

        CreateChefMateUserRequest userRequest = new CreateChefMateUserRequest();
        userRequest.setUserId(UUID.randomUUID().toString());
        userRequest.setUserPreferences(Optional.empty());
        userRequest.setRecipesTried(Optional.empty());
        userRequest.setIngredients(Optional.empty());

        ResultActions actions = mvc.perform(post("/user/createUser")
                        .content(mapper.writeValueAsString(userRequest))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        String responseBody = actions.andReturn().getResponse().getContentAsString();
        ChefMateUserResponse response = mapper.readValue(responseBody, ChefMateUserResponse.class);
        assertThat(response.getUserId()).isNotEmpty().as("The ID is populated");
        assertThat(response.getUserPreferences()).isNullOrEmpty();
        assertThat(response.getRecipesTried()).isNullOrEmpty();
        assertThat(response.getIngredients()).isNullOrEmpty();
    }

    /** ------------------------------------------------------------------------
     *  Update User
     *  ------------------------------------------------------------------------ **/

    @Test
    public void updateUserPreferences() throws Exception {

        // GIVEN
        CreateChefMateUserRequest userRequest = new CreateChefMateUserRequest();
        userRequest.setUserId(UUID.randomUUID().toString());
        userRequest.setUserPreferences(Optional.empty());
        userRequest.setRecipesTried(Optional.empty());
        userRequest.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse = chefMateUserService.addNewUser(userRequest);
        List<String> newUserPreferences = Arrays.asList("Gluten Free", "Vegetarian", "Dairy Free");


        UpdateUserPreferencesRequest updateRequest = new UpdateUserPreferencesRequest();
        updateRequest.setUserId(userResponse.getUserId());
        updateRequest.setUserPreferences(Optional.of(newUserPreferences));
        updateRequest.setRecipesTried(Optional.empty());
        updateRequest.setIngredients(Optional.empty());

        // WHEN
        ResultActions actions = mvc.perform(put("/user/userPreferences/{userPreferences}", newUserPreferences)
                        .content(mapper.writeValueAsString(updateRequest))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        // THEN
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        ChefMateUserResponse response = mapper.readValue(responseBody, ChefMateUserResponse.class);
        assertThat(response.getUserId()).isNotEmpty().as("The ID is populated");
        assertThat(response.getUserPreferences()).isNotEmpty().as("The user preference is populated");
        assertThat(response.getRecipesTried()).isNullOrEmpty();
        assertThat(response.getIngredients()).isNullOrEmpty();
    }

}
