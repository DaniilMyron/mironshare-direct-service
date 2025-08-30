package com.miron.directservice.infrastructure.entity;

import com.miron.directservice.domain.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UserEntity extends User {
    @Id
    private UUID id;
    public UserEntity(String username, String name, String profilePicture, String personalInformation, String gender) {
        super(username, name, profilePicture, personalInformation, gender);
    }

    public UserEntity() {
    }
}
