package com.miron.directservice.domain.spi;

import com.miron.directservice.domain.entity.Chat;
import com.miron.directservice.domain.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository {
    Message save(Message message);
    Message findById(UUID id);
    Message findBySenderId(UUID senderId);
    List<Message> findAll();
    void deleteById(UUID id);
    void deleteAllChatMessages(Chat chat);
}
