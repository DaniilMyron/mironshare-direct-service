package com.miron.directservice.domain.spi;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository<T extends Chat> {
    T save(T chat);
    Optional<T> findById(UUID id);
    List<T> findAll();
    void deleteById(UUID id);

    List<T> findByUser(User user);
}
