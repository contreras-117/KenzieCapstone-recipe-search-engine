package com.kenzie.appserver.controller;


import com.kenzie.appserver.Service.ChefMateUserService;
import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.controller.model.UpdateRecipesTriedRequest;
import com.kenzie.appserver.controller.model.UpdateUserPreferencesRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class ChefMateUserController {

    private ChefMateUserService chefMateUserService;

    ChefMateUserController(ChefMateUserService chefMateUserService) {
        this.chefMateUserService = chefMateUserService;
    }
//
//    @GetMapping("/userProfile")
//    public AuthUserProfile getUserProfile(@RequestHeader("Authorization") String authorizationHeader) {
//       /* String accessToken = extractAccessToken(authorizationHeader);
//        OAuth2ResourceServerProperties.Jwt jwt = decodeAccessToken(accessToken);
//        String userId = jwt.;
//        String email = jwt.getClaim("email").asString();
//        return new AuthUserProfile(userId, email);
//        */
//    return null;
//    }


    @PostMapping("/createUser")
    public ResponseEntity<ChefMateUserResponse> addNewUser(@RequestBody CreateChefMateUserRequest createChefMateUserRequest) {

        if (createChefMateUserRequest.getUserId() == null || createChefMateUserRequest.getUserId().length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User");
        }

        ChefMateUserResponse response = chefMateUserService.addNewUser(createChefMateUserRequest);
        return ResponseEntity.created(URI.create("/user/createUser" + response.getUserId())).body(response);
    }

    @PutMapping("/userPreferences/{userPreferences}")
    public ResponseEntity<ChefMateUserResponse> updateUserPreferences(@PathVariable("userPreferences") List<String> userPreferences, @RequestBody UpdateUserPreferencesRequest updateUserPreferencesRequest) {

        ChefMateUserResponse response = chefMateUserService.updateUserPreferences(updateUserPreferencesRequest.getUserId(), updateUserPreferencesRequest.getUserPreferences().orElse(Collections.emptyList()));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/recipesTried/{recipesTried}")
    public ResponseEntity<ChefMateUserResponse> updateRecipesTried(@PathVariable("recipesTried") Set<String> recipesTried, @RequestBody UpdateRecipesTriedRequest updateRecipesTriedRequest) {
        ChefMateUserResponse response = chefMateUserService.updateRecipesTried(updateRecipesTriedRequest.getUserId(), updateRecipesTriedRequest.getRecipesTried().orElse(Collections.emptySet()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<ChefMateUserResponse> getUserById(@PathVariable("userId") String userId) {
        ChefMateUserResponse userResponse = chefMateUserService.getUserById(userId);
        if (userResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") String userId) {
        chefMateUserService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/review/createReview")
    public ResponseEntity<ReviewResponse> addReview(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        if (reviewCreateRequest.getReviewerId() == null || reviewCreateRequest.getRecipeId() ==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Id or Recipe Ids are null");
        }
        ReviewResponse reviewResponse = chefMateUserService.addReview(reviewCreateRequest);
        return ResponseEntity.created(URI.create("/review/createReview" + reviewResponse.getReviewerId() + reviewResponse.getRecipeId())).body(reviewResponse);
    }
    @GetMapping("/{userId}/recipes/food/search/nutrients/{query}")
    public ResponseEntity<List<RecipeResponse>> searchByNutrients(@PathVariable("query") String query,
                                                                  @PathVariable("userId") String userId) {

        ChefMateUserResponse chefMateUserResponse = chefMateUserService.getUserById(userId);

        if (chefMateUserResponse == null || chefMateUserResponse.getUserId() == null || chefMateUserResponse.getUserId().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(chefMateUserService.searchByNutrients(query));
    }


    @GetMapping("review/list/{recipeId}")
    public ResponseEntity<List<ReviewResponse>> getRecipeReviews(@PathVariable("recipeId") String recipeId) {
        if (recipeId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recipe Ids cannot be null");
        }
        List<ReviewResponse> reviewResponses = chefMateUserService.getRecipeReviews(recipeId);
        return ResponseEntity.ok(reviewResponses);
    }

       /* -----------------------------------------------------------------------------------------------------------
        Private Methods
       ----------------------------------------------------------------------------------------------------------- */
}
