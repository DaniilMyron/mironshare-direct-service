package com.miron.directservice.domain.valueObject;

import java.util.UUID;

public class User implements ValueObject<UUID>{
    private final UUID id;
    private String username;
    private String name;
    private String gender;
    private String profilePicture;
    private String personalInformation;

    public User(String username, String name, String profilePicture, String personalInformation, String gender) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.name = name;
        this.profilePicture = profilePicture;
        this.personalInformation = personalInformation;
        this.gender = gender;
    }

    @Override
    public UUID getValue() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getPersonalInformation() {
        return personalInformation;
    }

    public String getGender() {
        return gender;
    }
}
