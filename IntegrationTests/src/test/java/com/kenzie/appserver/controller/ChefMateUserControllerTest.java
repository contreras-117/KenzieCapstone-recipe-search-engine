package com.kenzie.appserver.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.Service.ChefMateUserService;
import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.controller.model.UpdateUserPreferencesRequest;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
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

    @Test
    public void updateRecipesTried() throws Exception {

        // GIVEN
        CreateChefMateUserRequest userRequest = new CreateChefMateUserRequest();
        userRequest.setUserId(UUID.randomUUID().toString());
        userRequest.setUserPreferences(Optional.empty());
        userRequest.setRecipesTried(Optional.empty());
        userRequest.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse = chefMateUserService.addNewUser(userRequest);
        Set<String> newRecipesTried = new HashSet<>();
        newRecipesTried.add("98765");
        newRecipesTried.add("Chinese Recipe");

        UpdateUserPreferencesRequest updateRequest = new UpdateUserPreferencesRequest();
        updateRequest.setUserId(userResponse.getUserId());
        updateRequest.setUserPreferences(Optional.empty());
        updateRequest.setRecipesTried(Optional.of(newRecipesTried));
        updateRequest.setIngredients(Optional.empty());

        // WHEN
        ResultActions actions = mvc.perform(put("/user/recipesTried/{recipesTried}", newRecipesTried)
                        .content(mapper.writeValueAsString(updateRequest))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        // THEN
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        ChefMateUserResponse response = mapper.readValue(responseBody, ChefMateUserResponse.class);
        assertThat(response.getUserId()).isNotEmpty().as("The ID is populated");
        assertThat(response.getUserPreferences()).isNullOrEmpty();
        assertThat(response.getRecipesTried()).isNotEmpty().as("The recipes tried is populated");
        assertThat(response.getIngredients()).isNullOrEmpty();
    }

    /** ------------------------------------------------------------------------
     *  Get User by ID
     *  ------------------------------------------------------------------------ **/
    @Test
    public void getUserById() throws Exception {

        // GIVEN
        CreateChefMateUserRequest userRequest = new CreateChefMateUserRequest();
        userRequest.setUserId(UUID.randomUUID().toString());
        userRequest.setUserPreferences(Optional.empty());
        userRequest.setRecipesTried(Optional.empty());
        userRequest.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse = chefMateUserService.addNewUser(userRequest);

        // WHEN
        ResultActions actions = mvc.perform(get("/user/getUserById/{userId}", userResponse.getUserId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // THEN
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        ChefMateUserResponse response = mapper.readValue(responseBody, ChefMateUserResponse.class);

        assertThat(response.getUserId()).isNotEmpty().as("The ID is populated");
    }

    /** ------------------------------------------------------------------------
     *  Delete User
     *  ------------------------------------------------------------------------ **/

    @Test
    public void deleteUser_successful() throws Exception {

        // GIVEN
        CreateChefMateUserRequest userRequest = new CreateChefMateUserRequest();
        userRequest.setUserId(UUID.randomUUID().toString());
        userRequest.setUserPreferences(Optional.empty());
        userRequest.setRecipesTried(Optional.empty());
        userRequest.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse = chefMateUserService.addNewUser(userRequest);

        // WHEN
        mvc.perform(delete("/user/deleteUser/{userId}", userResponse.getUserId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        // THEN
        mvc.perform(get("/user/getUserById/{userId}", userResponse.getUserId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addReview_successful() throws Exception {

        CreateChefMateUserRequest userRequest1 = new CreateChefMateUserRequest();
        CreateChefMateUserRequest userRequest2 = new CreateChefMateUserRequest();

        String recipeId = UUID.randomUUID().toString();
        String recipeId2 = UUID.randomUUID().toString();
        Set<String> recipesTried = new HashSet<>();
        recipesTried.add(recipeId);
        recipesTried.add(recipeId2);

        userRequest1.setUserId(UUID.randomUUID().toString());
        userRequest1.setUserPreferences(Optional.empty());
        userRequest1.setRecipesTried(Optional.of(recipesTried));
        userRequest1.setIngredients(Optional.empty());

        userRequest2.setUserId(UUID.randomUUID().toString());
        userRequest2.setUserPreferences(Optional.empty());
        userRequest2.setRecipesTried(Optional.of(recipesTried));
        userRequest2.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse1 = chefMateUserService.addNewUser(userRequest1);
        ChefMateUserResponse userResponse2 = chefMateUserService.addNewUser(userRequest2);

        ReviewCreateRequest createRequest1 = new ReviewCreateRequest();
        createRequest1.setReviewerId(userRequest1.getUserId());
        createRequest1.setRecipeId(recipeId);
        createRequest1.setComment("Test recipe");
        createRequest1.setRating(5.0);

        ReviewCreateRequest createRequest2 = new ReviewCreateRequest();
        createRequest2.setReviewerId(userRequest2.getUserId());
        createRequest2.setRecipeId(recipeId2);
        createRequest2.setComment("Test recipe");
        createRequest2.setRating(5.0);

        ReviewResponse reviewResponse = chefMateUserService.addReview(createRequest1);
        ReviewResponse reviewResponse2 = chefMateUserService.addReview(createRequest2);

        mvc.perform(get("/user/review/list/{recipeId}", createRequest1.getRecipeId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getReviews_successful() throws Exception {
        CreateChefMateUserRequest userRequest1 = new CreateChefMateUserRequest();
        CreateChefMateUserRequest userRequest2 = new CreateChefMateUserRequest();

        String recipeId = "98765";
        Set<String> recipesTried = new HashSet<>();
        recipesTried.add(recipeId);

        userRequest1.setUserId("UserIdTest");
        userRequest1.setUserPreferences(Optional.empty());
        userRequest1.setRecipesTried(Optional.of(recipesTried));
        userRequest1.setIngredients(Optional.empty());

        userRequest2.setUserId(UUID.randomUUID().toString());
        userRequest2.setUserPreferences(Optional.empty());
        userRequest2.setRecipesTried(Optional.of(recipesTried));
        userRequest2.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse1 = chefMateUserService.addNewUser(userRequest1);
        ChefMateUserResponse userResponse2 = chefMateUserService.addNewUser(userRequest2);

        ReviewCreateRequest createRequest1 = new ReviewCreateRequest();
        createRequest1.setReviewerId(userRequest1.getUserId());
        createRequest1.setRecipeId(recipeId);
        createRequest1.setComment("Good Recipe");
        createRequest1.setRating(4.0);

        ReviewCreateRequest createRequest2 = new ReviewCreateRequest();
        createRequest2.setReviewerId(userRequest2.getUserId());
        createRequest2.setRecipeId(recipeId);
        createRequest2.setComment("Great Recipe");
        createRequest2.setRating(5.0);

        ReviewResponse reviewResponse = chefMateUserService.addReview(createRequest1);
        ReviewResponse reviewResponse2 = chefMateUserService.addReview(createRequest2);

        ResultActions actions = mvc.perform(get("/user/review/list/{recipeId}", createRequest1.getRecipeId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        List<ReviewResponse> responses = mapper.readValue(responseBody, new TypeReference<List<ReviewResponse>>() {});

        for (ReviewResponse response : responses) {
            assertThat(response.getReviewerId()).isNotEmpty().as("The ID is populated");
            assertThat(response.getComment()).isNotEmpty().as("The Comment is populated");
            Assertions.assertEquals(response.getRecipeId(), recipeId, "recipe Ids should be the same");
        }
    }

    @Test
    void getAllRecipes_successful() throws Exception {
        CreateChefMateUserRequest userRequest1 = new CreateChefMateUserRequest();

        String recipeId = UUID.randomUUID().toString();
        Set<String> recipesTried = new HashSet<>();
        recipesTried.add(recipeId);

        userRequest1.setUserId(UUID.randomUUID().toString());
        userRequest1.setUserPreferences(Optional.empty());
        userRequest1.setRecipesTried(Optional.of(recipesTried));
        userRequest1.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse1 = chefMateUserService.addNewUser(userRequest1);

        ResultActions actions = mvc.perform(get("/user/recipes/food/search/{query}", "chicken")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        List<RecipeResponse> responses = mapper.readValue(responseBody, new TypeReference<List<RecipeResponse>>() {});

        for (RecipeResponse response : responses) {
            assertThat(response.getRecipeId()).isNotEmpty().as("The ID is populated");
        }
    }

    @Test
    void getRandomRecipe_success() throws Exception {
        CreateChefMateUserRequest userRequest1 = new CreateChefMateUserRequest();

        String recipeId = UUID.randomUUID().toString();
        Set<String> recipesTried = new HashSet<>();
        recipesTried.add(recipeId);

        userRequest1.setUserId(UUID.randomUUID().toString());
        userRequest1.setUserPreferences(Optional.empty());
        userRequest1.setRecipesTried(Optional.of(recipesTried));
        userRequest1.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse1 = chefMateUserService.addNewUser(userRequest1);

        ResultActions actions = mvc.perform(get("/user/recipes/random")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        List<RecipeResponse> responses = mapper.readValue(responseBody, new TypeReference<List<RecipeResponse>>() {});

        for (RecipeResponse response : responses) {
            assertThat(response.getRecipeId()).isNotEmpty().as("The ID is populated");
        }
    }

    @Test
    public void searchByNutrients() throws Exception {
        CreateChefMateUserRequest userRequest = new CreateChefMateUserRequest();

        String recipeId = UUID.randomUUID().toString();
        Set<String> recipesTried = new HashSet<>();
        recipesTried.add(recipeId);

        userRequest.setUserId(UUID.randomUUID().toString());
        userRequest.setUserPreferences(Optional.empty());
        userRequest.setRecipesTried(Optional.of(recipesTried));
        userRequest.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse = chefMateUserService.addNewUser(userRequest);

        String query = "minCalories=1200";

        ResultActions actions = mvc.perform(get("/user/recipes/food/search/nutrients/{query}", query)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        List<RecipeResponse> responses = mapper.readValue(responseBody, new TypeReference<List<RecipeResponse>>() {});

        for (RecipeResponse response : responses) {
            assertThat(response.getRecipeId()).isNotEmpty().as("The ID is populated");
        }
    }

    @Test
    public void searchByIngredients() throws Exception {
        CreateChefMateUserRequest userRequest = new CreateChefMateUserRequest();

        String recipeId = UUID.randomUUID().toString();
        Set<String> recipesTried = new HashSet<>();
        recipesTried.add(recipeId);

        userRequest.setUserId(UUID.randomUUID().toString());
        userRequest.setUserPreferences(Optional.empty());
        userRequest.setRecipesTried(Optional.of(recipesTried));
        userRequest.setIngredients(Optional.empty());

        ChefMateUserResponse userResponse = chefMateUserService.addNewUser(userRequest);

        String query = "apples,flour,sugar";

        ResultActions actions = mvc.perform(get("/user/recipes/food/search/ingredients/{query}", query)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        List<RecipeResponse> responses = mapper.readValue(responseBody, new TypeReference<List<RecipeResponse>>() {});

        for (RecipeResponse response : responses) {
            assertThat(response.getRecipeId()).isNotEmpty().as("The ID is populated");
        }
    }
}
