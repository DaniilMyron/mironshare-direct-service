package com.miron.directservice.infrastructure.controller.model;

import com.miron.directservice.domain.entity.User;

import java.util.UUID;

public record UserRequest(UUID id, String username, String user, String picture, String about) {
    public UserRequest(User user){
        this(user.getValue(), user.getUsername(), user.getName(), user.getProfilePicture(), user.getPersonalInformation());
    }
}
