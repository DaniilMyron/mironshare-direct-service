package com.miron.directservice.domain.entity;

import com.miron.directservice.domain.valueObject.ValueObject;
import lombok.Getter;

import java.util.UUID;

public abstract class User implements ValueObject<UUID> {
    private final UUID id;
    @Getter
    private String username;
    @Getter
    private String name;
    @Getter
    private String profilePicture;
    @Getter
    private String personalInformation;
    @Getter
    private String gender;

    protected User() {this.id = UUID.randomUUID();}

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

    public String getUserInfo() {
        return "Id: %s\nUsername: %s\nName: %s\nProfile picture: %s\nPersonal info: %s\nGender: %s".formatted(this.id,  this.username, this.name, this.profilePicture, this.personalInformation, this.gender);
    }
}
