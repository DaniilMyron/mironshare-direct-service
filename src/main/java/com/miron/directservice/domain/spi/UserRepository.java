package com.miron.directservice.domain.spi;

import com.miron.directservice.domain.valueObject.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUsername(String username);

    List<User> findAll();
}
