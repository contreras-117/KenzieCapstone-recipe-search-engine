package com.kenzie.appserver.controller;


import com.kenzie.appserver.controller.model.ChefMateUserResponse;
import com.kenzie.appserver.controller.model.CreateChefMateUserRequest;
import com.kenzie.appserver.service.ChefMateUserService;
import com.kenzie.appserver.service.model.AuthUserProfile;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class ChefMateUserController {

    private ChefMateUserService chefMateUserService;

    ChefMateUserController(ChefMateUserService chefMateUserService) {
        this.chefMateUserService = chefMateUserService;
    }

    @GetMapping("/userProfile")
    public AuthUserProfile getUserProfile(@RequestHeader("Authorization") String authorizationHeader) {
       /* String accessToken = extractAccessToken(authorizationHeader);
        OAuth2ResourceServerProperties.Jwt jwt = decodeAccessToken(accessToken);
        String userId = jwt.;
        String email = jwt.getClaim("email").asString();
        return new AuthUserProfile(userId, email);
        */
    return null;
    }


    @PostMapping("/createUser")
    public ResponseEntity<ChefMateUserResponse> addNewUser(@RequestBody CreateChefMateUserRequest createChefMateUserRequest) {

        return null;
    }

    @PostMapping("/updateUser/{userId}")
    public ResponseEntity<ChefMateUserResponse> updateUser(@RequestBody CreateChefMateUserRequest chefMateUserRequest) {
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
