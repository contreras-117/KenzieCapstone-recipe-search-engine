package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.controller.model.UpdateRecipesTriedRequest;
import com.kenzie.appserver.controller.model.UpdateUserPreferencesRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class ChefMateUserController {
//
//    private ChefMateUserService chefMateUserService;
//
//    ChefMateUserController(ChefMateUserService chefMateUserService) {
//        this.chefMateUserService = chefMateUserService;
//    }
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

        return null;
    }

    @PutMapping("/userPreferences/{userPreferences}")
    public ResponseEntity<ChefMateUserResponse> updateUserPreferences(@PathVariable("userPreferences") List<String> userPreferences, @RequestBody UpdateUserPreferencesRequest updateUserPreferencesRequest) {
        return null;
    }

    @PutMapping("/recipesTried/{recipesTried}")
    public ResponseEntity<ChefMateUserResponse> updateRecipesTried(@PathVariable("recipesTried") Set<String> recipesTried, @RequestBody UpdateRecipesTriedRequest updateRecipesTriedRequest) {
        return null;
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<ChefMateUserResponse> getUserById(@PathVariable("userId") String userId) {
        return null;
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") String userId) {
        return null;
    }




       /* -----------------------------------------------------------------------------------------------------------
        Private Methods
       ----------------------------------------------------------------------------------------------------------- */


}
