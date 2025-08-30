package com.miron.directservice.domain.spi;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository {
    Message save(Message message);
    Optional<Message> findById(UUID id);
    Optional<Message> findBySenderId(UUID senderId);
    List<Message> findAll();
    void deleteById(UUID id);
    void deleteAllChatMessages(Chat chat);
}
