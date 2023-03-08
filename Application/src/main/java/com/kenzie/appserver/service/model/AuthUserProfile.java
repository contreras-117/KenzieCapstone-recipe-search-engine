package com.kenzie.appserver.service.model;

public class AuthUserProfile {

    private final String userId;
    private final String email;

    public AuthUserProfile(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}
