package com.miron.directservice.domain.repository;

import com.miron.directservice.domain.spi.UserRepository;
import com.miron.directservice.domain.springAnnotations.DomainRepository;
import com.miron.directservice.domain.valueObject.User;

import java.util.*;

@DomainRepository
public class UserRepositoryInMemory implements UserRepository {
    private final Map<UUID, User> users = new HashMap<>();
    @Override
    public User save(User user) {
        users.computeIfPresent(user.getValue(), (key, value) -> user);
        users.putIfAbsent(user.getValue(), user);
        return users.get(user.getValue());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User foundedUser = null;
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                foundedUser = user;
                break;
            }
        }
        return Optional.ofNullable(foundedUser);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
